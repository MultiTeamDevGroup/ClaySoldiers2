package multiteam.claysoldiers2.main.networking;

import multiteam.claysoldiers2.ClaySoldiers2;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Networking {

    private static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextId(){
        return ID++;
    }

    public static void registerMessages(){
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(ClaySoldiers2.MOD_ID, "claysoldiers2"), () -> "1.0", s -> true, s -> true);

        INSTANCE.messageBuilder(UpdateClientSoldierPacket.class, nextId()).encoder(UpdateClientSoldierPacket::toBytes).decoder(UpdateClientSoldierPacket::new).consumer(UpdateClientSoldierPacket::handle).add();
    }
}
