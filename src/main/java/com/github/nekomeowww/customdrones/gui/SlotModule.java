package com.github.nekomeowww.customdrones.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import com.github.nekomeowww.customdrones.drone.DroneInfo;
import com.github.nekomeowww.customdrones.drone.module.Module;
import com.github.nekomeowww.customdrones.entity.EntityDrone;

public class SlotModule
{
    public static Minecraft mc = null;
    public int id;
    public int posX;
    public int posY;
    public int sizeX;
    public int sizeY;
    public Module module;
    public EntityDrone drone;
    public int modIndex = -1;
    public int overlayColor = -1;
    public boolean hovering = false;
    public int hoveringColor = -1996488705;

    public SlotModule(int i, int x, int y, int size, EntityDrone d, int index)
    {
        this.id = i;
        this.posX = x;
        this.posY = y;
        this.sizeX = (this.sizeY = size);
        this.drone = d;
        this.modIndex = index;
    }

    public SlotModule(int i, int x, int y, int xw, int yw, Module mod)
    {
        this.id = i;
        this.posX = x;
        this.posY = y;
        this.sizeX = xw;
        this.sizeY = yw;
        this.module = mod;
    }

    public void slotClicked(int mX, int mY, int mBut) {}

    public void updateSlot()
    {
        if ((this.drone != null) && (this.modIndex != -1)) {
            if (this.modIndex < this.drone.droneInfo.mods.size())
            {
                if (this.module != this.drone.droneInfo.mods.get(this.modIndex)) {
                    this.module = ((Module)this.drone.droneInfo.mods.get(this.modIndex));
                }
            }
            else {
                this.module = null;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void drawModule()
    {
        drawModule(this.posX, this.posY, this.posX + this.sizeX, this.posY + this.sizeY);
    }

    @SideOnly(Side.CLIENT)
    public void drawModule(double x0, double y0, double x1, double y1)
    {
        if (mc == null) {
            mc = Minecraft.getMinecraft();
        }
        if (mc != null)
        {
            mc.renderEngine.bindTexture((this.module == null ? Module.useless1 : this.module).texture);
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(3553);
            GL11.glDisable(2896);
            GL11.glBegin(7);
            GL11.glTexCoord2d(0.0D, 0.0D);
            GL11.glVertex2d(x0, y0);
            GL11.glTexCoord2d(0.0D, 1.0D);
            GL11.glVertex2d(x0, y1);
            GL11.glTexCoord2d(1.0D, 1.0D);
            GL11.glVertex2d(x1, y1);
            GL11.glTexCoord2d(1.0D, 0.0D);
            GL11.glVertex2d(x1, y0);
            GL11.glEnd();
            GL11.glDisable(2896);
            GL11.glPopMatrix();
            int marginX = (int)(0.125D * this.sizeX);
            int marginY = (int)(0.125D * this.sizeY);
            if (this.overlayColor != -1)
            {
                GL11.glPushMatrix();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                drawRect(this.posX + marginX, this.posY + marginY, this.posX + this.sizeX - marginX, this.posY + this.sizeY - marginY, this.overlayColor);
                GL11.glPopMatrix();
            }
            if (this.hovering)
            {
                GL11.glPushMatrix();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                drawRect(this.posX + marginX, this.posY + marginY, this.posX + this.sizeX - marginX, this.posY + this.sizeY - marginY, this.hoveringColor);
                GL11.glPopMatrix();
            }
        }
    }

    public static void drawRect(int left, int top, int right, int bottom, int color)
    {
        if (left < right)
        {
            int i = left;
            left = right;
            right = i;
        }
        if (top < bottom)
        {
            int j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (color >> 24 & 0xFF) / 255.0F;
        float f = (color >> 16 & 0xFF) / 255.0F;
        float f1 = (color >> 8 & 0xFF) / 255.0F;
        float f2 = (color & 0xFF) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer worldrenderer = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, top, 0.0D).endVertex();
        worldrenderer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}
