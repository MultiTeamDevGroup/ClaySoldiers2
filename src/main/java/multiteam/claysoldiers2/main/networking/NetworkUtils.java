package multiteam.claysoldiers2.main.networking;

import multiteam.claysoldiers2.main.Registration;
import multiteam.claysoldiers2.main.modifiers.modifier.CSModifier;
import multiteam.claysoldiers2.main.util.ExceptionUtils;
import net.minecraft.network.FriendlyByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Networking utilities for Clay Soldiers 2.
 *
 * @author Qboi123
 * @see Networking
 */
public final class NetworkUtils {
    private NetworkUtils() {
        // Utility class.
        throw ExceptionUtils.utilityClassException();
    }

    /**
     * Read a modifier list from Minecraft's byte buffer.
     *
     * @param buf byte buffer to read the modifiers from.
     * @return read modifiers.
     * @see #readModifierInstance(FriendlyByteBuf)
     */
    public static List<CSModifier.Instance> readModifierList(FriendlyByteBuf buf) {
        int size = buf.readInt(); // Read list size.

        // Read all modifier instances.
        List<CSModifier.Instance> out = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            out.add(readModifierInstance(buf));
        }

        return out;
    }

    /**
     * Read a single modifier instance.
     *
     * @param buf byte buffer to read the modifier instance from.
     * @return the read modifier instance.
     */
    private static CSModifier.Instance readModifierInstance(FriendlyByteBuf buf) {
        // Read registry name, and get the value from that.
        CSModifier modifier = Registration.getModifierRegistry().getValue(buf.readResourceLocation());

        // Read the amount.
        int amount = buf.readInt();

        return new CSModifier.Instance(modifier, amount);
    }

    /**
     * Write a modifier list to Minecraft's byte buffer.
     *
     * @param buf  byte buffer to write the modifiers to.
     * @param list modifiers to write.
     */
    public static void writeModifierList(FriendlyByteBuf buf, List<CSModifier.Instance> list) {
        buf.writeInt(list.size());
        for (CSModifier.Instance instance : list) {
            writeModifierInstance(buf, instance);
        }
    }

    /**
     * Write a single modifier instance.
     *
     * @param buf      byte buffer to write the modifier instance to.
     * @param instance instance to write.
     */
    private static void writeModifierInstance(FriendlyByteBuf buf, CSModifier.Instance instance) {
        buf.writeResourceLocation(Objects.requireNonNull(instance.getModifier().getRegistryName()));
        buf.writeInt(instance.getAmount());
    }
}
