package com.github.nekomeowww.customdrones.api.model;

import java.util.List;
import net.minecraft.util.math.Vec3d;

public class CMVertex
        extends CMBase
{
    public Vec3d pos;
    public Vec3d uv;
    public Vec3d normal;

    public CMVertex(Vec3d v1)
    {
        this.pos = v1;
    }

    public CMVertex(Vec3d v1, Vec3d v2)
    {
        this.pos = v1;
        this.uv = v2;
    }

    public CMVertex(Vec3d v1, Vec3d v2, Vec3d v3)
    {
        this.pos = v1;
        this.uv = v2;
        this.normal = v3;
    }

    public static Vec3d getMid(List<CMVertex> verts)
    {
        Vec3d total = new Vec3d(0.0D, 0.0D, 0.0D);
        if (verts.isEmpty()) {
            return total;
        }
        for (CMVertex vec : verts) {
            total = total.add(vec.pos);
        }
        return scale(total, 1.0D / verts.size());
    }

    public CMVertex getClosest(List<CMVertex> verts)
    {
        if (verts.size() > 0)
        {
            CMVertex v0 = (CMVertex)verts.get(0);
            double distSqr = this.pos.squareDistanceTo(v0.pos);
            for (int a = 0; a < verts.size(); a++)
            {
                CMVertex v1 = (CMVertex)verts.get(a);
                double thisDistSqr = this.pos.squareDistanceTo(v1.pos);
                if (thisDistSqr < distSqr)
                {
                    v0 = v1;
                    distSqr = thisDistSqr;
                }
            }
            return v0;
        }
        return null;
    }

    public void render()
    {
        super.render();
        begin(0);
        addToDrawing();
        end();
    }

    public void addToDrawing()
    {
        if (this.normal != null) {
            normal(this.normal.xCoord, this.normal.yCoord, this.normal.zCoord);
        }
        vertex(this.pos.xCoord, this.pos.yCoord, this.pos.zCoord, this.uv != null ? this.uv.xCoord : 0.0D, this.uv != null ? this.uv.yCoord : 0.0D);
    }

    public String toString()
    {
        String s = "Vert: " + this.pos + " UV: " + this.uv;
        if (this.normal != null) {
            s = s + " Normal: " + this.normal;
        }
        return s;
    }

    public boolean equals(Object obj)
    {
        if ((obj instanceof CMVertex)) {
            return (this.pos.equals(((CMVertex)obj).pos)) && ((this.normal == ((CMVertex)obj).normal) || (this.normal.equals(((CMVertex)obj).normal)));
        }
        return super.equals(obj);
    }
}

