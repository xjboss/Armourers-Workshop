
package riskyken.armourersWorkshop.client.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.model.ClientModelCache;
import riskyken.armourersWorkshop.client.model.equipmet.IEquipmentModel;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.utils.EquipmentNBTHelper;

/**
 * Helps render item stacks.
 * 
 * @author RiskyKen
 *
 */

@SideOnly(Side.CLIENT)
public final class ItemStackRenderHelper {

    public static void renderItemAsArmourModel(ItemStack stack, boolean showSkinPaint) {
        if (EquipmentNBTHelper.stackHasSkinData(stack)) {
            SkinPointer skinData = EquipmentNBTHelper.getSkinPointerFromStack(stack);
            renderItemModelFromId(skinData.skinId, skinData.skinType, showSkinPaint);
        }
    }
    
    public static void renderItemAsArmourModel(ItemStack stack, ISkinType skinType, boolean showSkinPaint) {
        int equipmentId = EquipmentNBTHelper.getSkinIdFromStack(stack);
        renderItemModelFromId(equipmentId, skinType, showSkinPaint);
    }
    
    public static void renderItemModelFromId(int equipmentId, ISkinType skinType, boolean showSkinPaint) {
        IEquipmentModel targetModel = EquipmentModelRenderer.INSTANCE.getModelForEquipmentType(skinType);
        if (targetModel == null) {
            return;
        }
        
        Skin data = ClientModelCache.INSTANCE.getEquipmentItemData(equipmentId);
        if (data == null) {
            return;
        }
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        
        if (skinType == SkinTypeRegistry.skinHead) {
            GL11.glTranslatef(0F, 0.2F, 0F);
            targetModel.render(null, null, data, showSkinPaint);
        } else if (skinType == SkinTypeRegistry.skinChest) {
            GL11.glTranslatef(0F, -0.35F, 0F);
            targetModel.render(null, null, data, showSkinPaint);
        } else if (skinType == SkinTypeRegistry.skinLegs) {
            GL11.glTranslatef(0F, -1.2F, 0F);
            targetModel.render(null, null, data, showSkinPaint);
        } else if (skinType == SkinTypeRegistry.skinSkirt) {
            GL11.glTranslatef(0F, -1.0F, 0F);
            targetModel.render(null, null, data, showSkinPaint);
        } else if (skinType == SkinTypeRegistry.skinFeet) {
            GL11.glTranslatef(0F, -1.2F, 0F);
            targetModel.render(null, null, data, showSkinPaint);
        } else if (skinType == SkinTypeRegistry.skinSword) {
            targetModel.render(null, null, data, showSkinPaint);
        } else if (skinType == SkinTypeRegistry.skinBow) {
            targetModel.render(null, null, data, showSkinPaint);
        }
        
        GL11.glDisable(GL11.GL_BLEND);
    }
}
