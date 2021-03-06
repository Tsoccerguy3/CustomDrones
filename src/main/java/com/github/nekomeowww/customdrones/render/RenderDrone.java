package com.github.nekomeowww.customdrones.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.github.nekomeowww.customdrones.entity.EntityDrone;

public class RenderDrone<T extends EntityDrone>
        extends Render<T>
{
    public RenderDrone(RenderManager renderManager)
    {
        super(renderManager);
    }

    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        double d = 1.0D;
        GL11.glScaled(d, d, d);
        ModelDrone model = DroneModels.instance.getModelOrDefault(entity);
        model.doRender(entity, entityYaw, partialTicks, new Object[0]);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(T entity)
    {
        return null;
    }
}
