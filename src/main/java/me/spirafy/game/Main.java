package me.spirafy.game;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    public void hi(){
        this.getConfig().getConfigurationSection("");
    }


    @Override
    public void onEnable() {
        for (Plugin p : this.getServer().getPluginManager().getPlugins()) {
            System.out.println(p.getDescription().getVersion());
            System.out.println(p.getName());
        }
        String s = "sad";
        int ss = 10;
        System.out.println(1 >> ss);
        StringBuilder builder = new StringBuilder();
        for (char c : s.toCharArray()) {
            builder.append(c >> 1);
        }

        System.out.println(builder.toString());
        System.out.println("hellow wor");
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }/*

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ArmorStand stand = (ArmorStand) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.ARMOR_STAND);
        stand.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        CraftWorld world = null;
        world.getHandle().rayTrace();
        ChannelDuplexHandler handler = new ChannelDuplexHandler(){
            public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception {
                if (packet instanceof PacketPlayInVehicleMove) {
                }
                super.channelRead(ctx, packet);
            }

            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                super.write(ctx, msg, promise);
            }
        };

        ((CraftPlayer) e.getPlayer()).getHandle().playerConnection.networkManager.channel.pipeline()
                .addBefore("packet_handler", e.getPlayer().getName(), handler);

        e.getPlayer().getPassengers().add(stand);
    }

    @EventHandler
    public void onJoin(PlayerMoveEvent e) {
        List<Entity> entityList = e.getPlayer().getPassengers();
        for (Entity entity : entityList) {
            if (e.getPlayer().isInsideVehicle()) {
                if (e.getPlayer().getVehicle() instanceof Horse) {
                    Horse h = (Horse) e.getPlayer().getVehicle();
                    ((CraftArmorStand) entity).getHandle().setHeadRotation(h.getLocation().getYaw());

                }
            }
            ((CraftArmorStand) entity).getHandle().setHeadRotation(e.getPlayer().getLocation().getYaw());
        }
    }*/


}
