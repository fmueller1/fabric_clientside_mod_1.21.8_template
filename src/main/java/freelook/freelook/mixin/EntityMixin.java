package freelook.freelook.mixin;

import freelook.freelook.*;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector2d;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin implements CameraOverriddenEntity {
    @Shadow
    public abstract void setAngles(float yaw, float pitch);

    @Shadow
    @Final
    private static Logger LOGGER;
    @Shadow
    private float yaw;
    @Shadow
    private float pitch;
    @Unique
    private float cameraPitch;

    @Unique
    private float cameraYaw;

    @Inject(method = "changeLookDirection", at = @At("HEAD"), cancellable = true)
    public void changeCameraLookDirection(double xDelta, double yDelta, CallbackInfo ci) {
        //noinspection ConstantValue// IntelliJ is incorrect here, this code block is reachable
        if (!(FreeLookMod.isFreeLooking && (Object) this instanceof ClientPlayerEntity)) {
            return;
        }
        double pitchDelta = (yDelta * 0.15);
        double yawDelta = (xDelta * 0.15);

        this.cameraPitch = MathHelper.clamp(this.cameraPitch + (float) pitchDelta, -90.0f, 90.0f);
        this.cameraYaw += (float) yawDelta;
        if(cameraYaw >= 180){
            cameraYaw -= 360;
        }
        if(cameraYaw <= -180){
            cameraYaw += 360;
        }

        Vector2D viewVectorRadiens = new Vector2D(cameraYaw, cameraPitch).convertToRads();
        Vector2D out = Meth.targetInput(viewVectorRadiens, new MCCannonModel()).convertToDegrees();
        this.setAngles((float)out.x, (float)out.y);
        LOGGER.info("output: " + out.toString());
        LOGGER.info("actual view direction: " + this.yaw + ", " + this.pitch);

        ci.cancel();
    }

    @Override
    @Unique
    public float freelook$getCameraPitch() {
        return this.cameraPitch;
    }

    @Override
    @Unique
    public float freelook$getCameraYaw() {
        return this.cameraYaw;
    }

    @Override
    @Unique
    public void freelook$setCameraPitch(float pitch) {
        this.cameraPitch = pitch;
    }

    @Override
    @Unique
    public void freelook$setCameraYaw(float yaw) {
        this.cameraYaw = yaw;
    }
}