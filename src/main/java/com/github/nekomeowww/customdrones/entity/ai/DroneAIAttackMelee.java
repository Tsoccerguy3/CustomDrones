package com.github.nekomeowww.customdrones.entity.ai;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import com.github.nekomeowww.customdrones.api.helpers.EntityHelper;
import com.github.nekomeowww.customdrones.drone.DroneInfo;
import com.github.nekomeowww.customdrones.drone.module.Module;
import com.github.nekomeowww.customdrones.entity.EntityDroneMob;

public class DroneAIAttackMelee
        extends EntityAIBase
{
    public Random rnd;
    public EntityDroneMob drone;

    public DroneAIAttackMelee(EntityDroneMob m)
    {
        this.drone = m;
        this.rnd = new Random();
    }

    public boolean func_75250_a()
    {
        return this.drone.getDroneAttackTarget() != null;
    }

    public void func_75249_e()
    {
        super.func_75249_e();
        this.drone.droneInfo.switchModule(this.drone, this.drone.droneInfo.getModuleWithFunctionOf(Module.weapon1), true);
    }

    public void func_75251_c()
    {
        super.func_75251_c();
        this.drone.droneInfo.switchModule(this.drone, this.drone.droneInfo.getModuleWithFunctionOf(Module.weapon1), false);
    }

    public void func_75246_d()
    {
        super.func_75246_d();
        Entity target = this.drone.getDroneAttackTarget();
        this.drone.flyTo(EntityHelper.getCenterVec(target), 0.0D, 1.0D);
        if (((target instanceof EntityPlayer)) && (((EntityPlayer)target).field_71075_bZ.field_75102_a)) {
            this.drone.setDroneAttackTarget(null, true);
        }
    }
}
