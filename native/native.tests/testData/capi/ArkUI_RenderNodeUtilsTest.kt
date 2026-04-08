/*
 * Copyright (C) 2026 Eazytec. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class ArkUI_RenderNodeUtilsTest {
    private fun logLine(message: String) {
        println("[stdout] ArkUI_RenderNodeUtilsTest $message")
    }

    @Test
    fun testRenderNodeCreateAndDispose() {
        logLine("--- Testing ArkUI_RenderNodeUtils create & dispose (API 20) ---")
        try {
            testRenderNodeCreateAndDisposeInternal()
        } catch (e: Throwable) {
            logLine("ArkUI_RenderNodeUtils (API 20) exception: $e")
        }
    }

    @Test
    fun testRenderNodeChildOperations() {
        logLine("--- Testing ArkUI_RenderNodeUtils child operations (API 20) ---")
        try {
            testRenderNodeChildOperationsInternal()
        } catch (e: Throwable) {
            logLine("ArkUI_RenderNodeUtils (API 20) exception: $e")
        }
    }

    @Test
    fun testRenderNodeBasicProperties() {
        logLine("--- Testing ArkUI_RenderNodeUtils basic properties (API 20) ---")
        try {
            testRenderNodeBasicPropertiesInternal()
        } catch (e: Throwable) {
            logLine("ArkUI_RenderNodeUtils (API 20) exception: $e")
        }
    }

    @Test
    fun testRenderNodeTransformProperties() {
        logLine("--- Testing ArkUI_RenderNodeUtils transform properties (API 20) ---")
        try {
            testRenderNodeTransformPropertiesInternal()
        } catch (e: Throwable) {
            logLine("ArkUI_RenderNodeUtils (API 20) exception: $e")
        }
    }

    @Test
    fun testRenderNodeShadowProperties() {
        logLine("--- Testing ArkUI_RenderNodeUtils shadow properties (API 20) ---")
        try {
            testRenderNodeShadowPropertiesInternal()
        } catch (e: Throwable) {
            logLine("ArkUI_RenderNodeUtils (API 20) exception: $e")
        }
    }

    @Test
    fun testRenderNodeBorderProperties() {
        logLine("--- Testing ArkUI_RenderNodeUtils border properties (API 20) ---")
        try {
            testRenderNodeBorderPropertiesInternal()
        } catch (e: Throwable) {
            logLine("ArkUI_RenderNodeUtils (API 20) exception: $e")
        }
    }

    @Test
    fun testRenderNodeMaskAndClip() {
        logLine("--- Testing ArkUI_RenderNodeUtils mask & clip (API 20) ---")
        try {
            testRenderNodeMaskAndClipInternal()
        } catch (e: Throwable) {
            logLine("ArkUI_RenderNodeUtils (API 20) exception: $e")
        }
    }

    @Test
    fun testContentModifier() {
        logLine("--- Testing ArkUI_RenderNodeUtils content modifiers (API 20) ---")
        try {
            testContentModifierInternal()
        } catch (e: Throwable) {
            logLine("ArkUI_RenderNodeUtils (API 20) exception: $e")
        }
    }

    @Test
    fun testPropertyOperations() {
        logLine("--- Testing ArkUI_RenderNodeUtils properties (API 20) ---")
        try {
            testPropertyOperationsInternal()
        } catch (e: Throwable) {
            logLine("ArkUI_RenderNodeUtils (API 20) exception: $e")
        }
    }

    @Test
    fun testAnimatablePropertyOperations() {
        logLine("--- Testing ArkUI_RenderNodeUtils animatable properties (API 20) ---")
        try {
            testAnimatablePropertyOperationsInternal()
        } catch (e: Throwable) {
            logLine("ArkUI_RenderNodeUtils (API 20) exception: $e")
        }
    }

    @Test
    fun testShapeOptions() {
        logLine("--- Testing ArkUI_RenderNodeUtils shape options (API 20) ---")
        try {
            testShapeOptionsInternal()
        } catch (e: Throwable) {
            logLine("ArkUI_RenderNodeUtils (API 20) exception: $e")
        }
    }

    @Test
    fun testNodeHandleApis() {
        logLine("--- Testing ArkUI_RenderNodeUtils ArkUI_NodeHandle related APIs (API 20) ---")
        try {
            testNodeHandleApisInternal()
        } catch (e: Throwable) {
            logLine("ArkUI_RenderNodeUtils (API 20) exception: $e")
        }
    }

    @Test
    fun testEnums() {
        logLine("--- Testing ArkUI_RenderNodeUtils related enums ---")

        // ArkUI_EdgeDirection
        val edgeAll = platform.ArkUI.ArkUI_NativeModule.ARKUI_EDGE_DIRECTION_ALL
        val edgeLeft = platform.ArkUI.ArkUI_NativeModule.ARKUI_EDGE_DIRECTION_LEFT
        val edgeRight = platform.ArkUI.ArkUI_NativeModule.ARKUI_EDGE_DIRECTION_RIGHT
        val edgeTop = platform.ArkUI.ArkUI_NativeModule.ARKUI_EDGE_DIRECTION_TOP
        val edgeBottom = platform.ArkUI.ArkUI_NativeModule.ARKUI_EDGE_DIRECTION_BOTTOM
        logLine("ArkUI_EdgeDirection: ALL=$edgeAll LEFT=$edgeLeft RIGHT=$edgeRight TOP=$edgeTop BOTTOM=$edgeBottom")

        // ArkUI_CornerDirection
        val cornerAll = platform.ArkUI.ArkUI_NativeModule.ARKUI_CORNER_DIRECTION_ALL
        val cornerTopLeft = platform.ArkUI.ArkUI_NativeModule.ARKUI_CORNER_DIRECTION_TOP_LEFT
        val cornerTopRight = platform.ArkUI.ArkUI_NativeModule.ARKUI_CORNER_DIRECTION_TOP_RIGHT
        val cornerBottomLeft = platform.ArkUI.ArkUI_NativeModule.ARKUI_CORNER_DIRECTION_BOTTOM_LEFT
        val cornerBottomRight = platform.ArkUI.ArkUI_NativeModule.ARKUI_CORNER_DIRECTION_BOTTOM_RIGHT
        logLine("ArkUI_CornerDirection: ALL=$cornerAll TL=$cornerTopLeft TR=$cornerTopRight BL=$cornerBottomLeft BR=$cornerBottomRight")

        // ArkUI_BorderStyle
        val borderSolid = platform.ArkUI.ArkUI_NativeModule.ARKUI_BORDER_STYLE_SOLID
        val borderDashed = platform.ArkUI.ArkUI_NativeModule.ARKUI_BORDER_STYLE_DASHED
        val borderDotted = platform.ArkUI.ArkUI_NativeModule.ARKUI_BORDER_STYLE_DOTTED
        logLine("ArkUI_BorderStyle: SOLID=$borderSolid DASHED=$borderDashed DOTTED=$borderDotted")
    }

    // 下面是内部实现，每个函数内部可以使用 memScoped。

    private fun testRenderNodeCreateAndDisposeInternal() {
        memScoped {
            val node = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateNode()
            logLine("OH_ArkUI_RenderNodeUtils_CreateNode() result=$node")

            val disposeResult = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNode(node)
            logLine("OH_ArkUI_RenderNodeUtils_DisposeNode(node) result=$disposeResult")

            val disposeNullResult = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNode(null)
            logLine("OH_ArkUI_RenderNodeUtils_DisposeNode(null) result=$disposeNullResult")
        }
    }

    private fun testRenderNodeChildOperationsInternal() {
        memScoped {
            val parentNode = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateNode()
            val childNode = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateNode()

            val addChildResult = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_AddChild(parentNode, childNode)
            logLine("OH_ArkUI_RenderNodeUtils_AddChild(parent,child) result=$addChildResult")

            val insertChildAfterResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_InsertChildAfter(parentNode, childNode, null)
            logLine("OH_ArkUI_RenderNodeUtils_InsertChildAfter(parent,child,null) result=$insertChildAfterResult")

            val getChildResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetChild(parentNode, 0, null)
            logLine("OH_ArkUI_RenderNodeUtils_GetChild(parent,0,null) result=$getChildResult")

            val getFirstChildResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetFirstChild(parentNode, null)
            logLine("OH_ArkUI_RenderNodeUtils_GetFirstChild(parent,null) result=$getFirstChildResult")

            val getNextSiblingResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetNextSibling(childNode, null)
            logLine("OH_ArkUI_RenderNodeUtils_GetNextSibling(child,null) result=$getNextSiblingResult")

            val getPreviousSiblingResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetPreviousSibling(childNode, null)
            logLine("OH_ArkUI_RenderNodeUtils_GetPreviousSibling(child,null) result=$getPreviousSiblingResult")

            val getChildrenResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetChildren(parentNode, null, null)
            logLine("OH_ArkUI_RenderNodeUtils_GetChildren(parent,null,null) result=$getChildrenResult")

            val childrenCount = alloc<IntVar>()
            val getCountResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetChildrenCount(parentNode, childrenCount.ptr)
            logLine("OH_ArkUI_RenderNodeUtils_GetChildrenCount(parent,ptr) result=$getCountResult count=${childrenCount.value}")

            val removeChildResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_RemoveChild(parentNode, childNode)
            logLine("OH_ArkUI_RenderNodeUtils_RemoveChild(parent,child) result=$removeChildResult")

            val clearChildrenResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_ClearChildren(parentNode)
            logLine("OH_ArkUI_RenderNodeUtils_ClearChildren(parent) result=$clearChildrenResult")

            val addChildNullResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_AddChild(null, null)
            logLine("OH_ArkUI_RenderNodeUtils_AddChild(null,null) result=$addChildNullResult")

            val removeChildNullResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_RemoveChild(null, null)
            logLine("OH_ArkUI_RenderNodeUtils_RemoveChild(null,null) result=$removeChildNullResult")

            val disposeChildResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNode(childNode)
            val disposeParentResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNode(parentNode)
            logLine("Dispose child result=$disposeChildResult parent result=$disposeParentResult")
        }
    }

    private fun testRenderNodeBasicPropertiesInternal() {
        memScoped {
            val node = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateNode()

            val setBgColorResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetBackgroundColor(node, 0xFF0000FFu)
            val bgColor = alloc<UIntVar>()
            val getBgColorResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetBackgroundColor(node, bgColor.ptr)
            logLine("BGColor setResult=$setBgColorResult getResult=$getBgColorResult value=0x${bgColor.value.toString(16)}")

            val setOpacityResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetOpacity(node, 0.5f)
            val opacity = alloc<FloatVar>()
            val getOpacityResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetOpacity(node, opacity.ptr)
            logLine("Opacity setResult=$setOpacityResult getResult=$getOpacityResult value=${opacity.value}")

            val setSizeResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetSize(node, 100, 200)
            val width = alloc<IntVar>()
            val height = alloc<IntVar>()
            val getSizeResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetSize(node, width.ptr, height.ptr)
            logLine("Size setResult=$setSizeResult getResult=$getSizeResult width=${width.value} height=${height.value}")

            val setPositionResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetPosition(node, 10, 20)
            val x = alloc<IntVar>()
            val y = alloc<IntVar>()
            val getPositionResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetPosition(node, x.ptr, y.ptr)
            logLine("Position setResult=$setPositionResult getResult=$getPositionResult x=${x.value} y=${y.value}")

            val setClipToFrameResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetClipToFrame(node, 1)
            val clipToFrame = alloc<IntVar>()
            val getClipToFrameResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetClipToFrame(node, clipToFrame.ptr)
            logLine("ClipToFrame setResult=$setClipToFrameResult getResult=$getClipToFrameResult value=${clipToFrame.value}")

            val setClipToBoundsResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetClipToBounds(node, 1)
            val clipToBounds = alloc<IntVar>()
            val getClipToBoundsResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetClipToBounds(node, clipToBounds.ptr)
            logLine("ClipToBounds setResult=$setClipToBoundsResult getResult=$getClipToBoundsResult value=${clipToBounds.value}")

            val setBoundsResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetBounds(node, 0, 0, 100, 200)
            val boundsX = alloc<IntVar>()
            val boundsY = alloc<IntVar>()
            val boundsWidth = alloc<IntVar>()
            val boundsHeight = alloc<IntVar>()
            val getBoundsResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetBounds(
                    node, boundsX.ptr, boundsY.ptr, boundsWidth.ptr, boundsHeight.ptr)
            logLine("Bounds setResult=$setBoundsResult getResult=$getBoundsResult x=${boundsX.value} y=${boundsY.value} w=${boundsWidth.value} h=${boundsHeight.value}")

            val disposeResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNode(node)

        }
    }

    private fun testRenderNodeTransformPropertiesInternal() {
        memScoped {
            val node = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateNode()


            val setPivotResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetPivot(node, 0.5f, 0.5f)
            val pivotX = alloc<FloatVar>()
            val pivotY = alloc<FloatVar>()
            val getPivotResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetPivot(node, pivotX.ptr, pivotY.ptr)
            logLine("Pivot setResult=$setPivotResult getResult=$getPivotResult x=${pivotX.value} y=${pivotY.value}")


            val setScaleResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetScale(node, 1.5f, 1.5f)
            val scaleX = alloc<FloatVar>()
            val scaleY = alloc<FloatVar>()
            val getScaleResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetScale(node, scaleX.ptr, scaleY.ptr)
            logLine("Scale setResult=$setScaleResult getResult=$getScaleResult sx=${scaleX.value} sy=${scaleY.value}")


            val setTranslationResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetTranslation(node, 10.0f, 20.0f)
            val transX = alloc<FloatVar>()
            val transY = alloc<FloatVar>()
            val getTranslationResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetTranslation(node, transX.ptr, transY.ptr)
            logLine("Translation setResult=$setTranslationResult getResult=$getTranslationResult x=${transX.value} y=${transY.value}")


            val setRotationResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetRotation(node, 45.0f, 0.0f, 0.0f)
            val rotX = alloc<FloatVar>()
            val rotY = alloc<FloatVar>()
            val rotZ = alloc<FloatVar>()
            val getRotationResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetRotation(node, rotX.ptr, rotY.ptr, rotZ.ptr)
            logLine("Rotation setResult=$setRotationResult getResult=$getRotationResult x=${rotX.value} y=${rotY.value} z=${rotZ.value}")

            val matrix = allocArray<FloatVar>(16)
            matrix[0] = 1.0f
            val setTransformResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetTransform(node, matrix)
            logLine("SetTransform result=$setTransformResult")

            val disposeResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNode(node)

        }
    }

    private fun testRenderNodeShadowPropertiesInternal() {
        memScoped {
            val node = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateNode()

            val setShadowColorResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetShadowColor(node, 0xFF000000u)
            val shadowColor = alloc<UIntVar>()
            val getShadowColorResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetShadowColor(node, shadowColor.ptr)
            logLine("ShadowColor setResult=$setShadowColorResult getResult=$getShadowColorResult value=0x${shadowColor.value.toString(16)}")

            val setShadowOffsetResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetShadowOffset(node, 5, 5)
            val shadowOffsetX = alloc<IntVar>()
            val shadowOffsetY = alloc<IntVar>()
            val getShadowOffsetResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetShadowOffset(node, shadowOffsetX.ptr, shadowOffsetY.ptr)
            logLine("ShadowOffset setResult=$setShadowOffsetResult getResult=$getShadowOffsetResult x=${shadowOffsetX.value} y=${shadowOffsetY.value}")

            val setShadowAlphaResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetShadowAlpha(node, 0.5f)
            val shadowAlpha = alloc<FloatVar>()
            val getShadowAlphaResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetShadowAlpha(node, shadowAlpha.ptr)
            logLine("ShadowAlpha setResult=$setShadowAlphaResult getResult=$getShadowAlphaResult value=${shadowAlpha.value}")


            val setShadowElevationResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetShadowElevation(node, 10.0f)
            val shadowElevation = alloc<FloatVar>()
            val getShadowElevationResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetShadowElevation(node, shadowElevation.ptr)
            logLine("ShadowElevation setResult=$setShadowElevationResult getResult=$getShadowElevationResult value=${shadowElevation.value}")

            val setShadowRadiusResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetShadowRadius(node, 5.0f)
            val shadowRadius = alloc<FloatVar>()
            val getShadowRadiusResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetShadowRadius(node, shadowRadius.ptr)
            logLine("ShadowRadius setResult=$setShadowRadiusResult getResult=$getShadowRadiusResult value=${shadowRadius.value}")


            val disposeResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNode(node)

        }
    }

    private fun testRenderNodeBorderPropertiesInternal() {
        memScoped {
            val node = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateNode()

            val borderStyle = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateNodeBorderStyleOption()

            // Set edge style on border style option using enums.
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetNodeBorderStyleOptionEdgeStyle(
                borderStyle,
                platform.ArkUI.ArkUI_NativeModule.ARKUI_BORDER_STYLE_SOLID,
                platform.ArkUI.ArkUI_NativeModule.ARKUI_EDGE_DIRECTION_ALL
            )
            val setBorderStyleResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetBorderStyle(node, borderStyle)
            val getBorderStyleResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetBorderStyle(node, null)
            logLine("BorderStyle create=$borderStyle setResult=$setBorderStyleResult getResult=$getBorderStyleResult")
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNodeBorderStyleOption(borderStyle)

            val borderWidth = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateNodeBorderWidthOption()
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetNodeBorderWidthOptionEdgeWidth(
                borderWidth,
                2.0f,
                platform.ArkUI.ArkUI_NativeModule.ARKUI_EDGE_DIRECTION_ALL
            )
            val setBorderWidthResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetBorderWidth(node, borderWidth)
            val getBorderWidthResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetBorderWidth(node, null)
            logLine("BorderWidth create=$borderWidth setResult=$setBorderWidthResult getResult=$getBorderWidthResult")
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNodeBorderWidthOption(borderWidth)

            val borderColor = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateNodeBorderColorOption()
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetNodeBorderColorOptionEdgeColor(
                borderColor,
                0xFF00FF00u,
                platform.ArkUI.ArkUI_NativeModule.ARKUI_EDGE_DIRECTION_ALL
            )
            val setBorderColorResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetBorderColor(node, borderColor)
            val getBorderColorResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetBorderColor(node, null)
            logLine("BorderColor create=$borderColor setResult=$setBorderColorResult getResult=$getBorderColorResult")
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNodeBorderColorOption(borderColor)

            val borderRadius = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateNodeBorderRadiusOption()
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetNodeBorderRadiusOptionCornerRadius(
                borderRadius,
                8u,
                platform.ArkUI.ArkUI_NativeModule.ARKUI_CORNER_DIRECTION_ALL
            )
            val setBorderRadiusResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetBorderRadius(node, borderRadius)
            val getBorderRadiusResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetBorderRadius(node, null)
            logLine("BorderRadius create=$borderRadius setResult=$setBorderRadiusResult getResult=$getBorderRadiusResult")
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNodeBorderRadiusOption(borderRadius)

            val disposeResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNode(node)
        }
    }

    private fun testRenderNodeMaskAndClipInternal() {
        memScoped {
            val node = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateNode()

            // 基于 RectShape 的 mask/clip
            val rectShape = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRectShapeOption()
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetRectShapeOptionEdgeValue(
                rectShape,
                10.0f,
                platform.ArkUI.ArkUI_NativeModule.ARKUI_EDGE_DIRECTION_ALL
            )

            val maskFromRect =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRenderNodeMaskOptionFromRectShape(rectShape)
            logLine("CreateRenderNodeMaskOptionFromRectShape rectShape=$rectShape mask=$maskFromRect")

            val setMaskResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetMask(node, maskFromRect)
            logLine("SetMask using rect mask result=$setMaskResult")

            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetRenderNodeMaskOptionFillColor(
                maskFromRect,
                0xFF0000FFu
            )
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetRenderNodeMaskOptionStrokeColor(
                maskFromRect,
                0xFF00FF00u
            )
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetRenderNodeMaskOptionStrokeWidth(
                maskFromRect,
                2.0f
            )

            val clipFromRect =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRenderNodeClipOptionFromRectShape(rectShape)
            logLine("CreateRenderNodeClipOptionFromRectShape rectShape=$rectShape clip=$clipFromRect")

            val setClipResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetClip(node, clipFromRect)
            logLine("SetClip using rect clip result=$setClipResult")

            // RoundRect/Circle/Oval/CommandPath 派生的 mask/clip
            val roundRectShape =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRoundRectShapeOption()
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetRoundRectShapeOptionEdgeValue(
                roundRectShape,
                5.0f,
                platform.ArkUI.ArkUI_NativeModule.ARKUI_EDGE_DIRECTION_ALL
            )
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetRoundRectShapeOptionCornerXY(
                roundRectShape,
                0.0f,
                0.0f,
                platform.ArkUI.ArkUI_NativeModule.ARKUI_CORNER_DIRECTION_ALL
            )

            val maskFromRoundRect =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRenderNodeMaskOptionFromRoundRectShape(
                    roundRectShape
                )
            logLine("CreateRenderNodeMaskOptionFromRoundRectShape roundRect=$roundRectShape mask=$maskFromRoundRect")

            val clipFromRoundRect =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRenderNodeClipOptionFromRoundRectShape(
                    roundRectShape
                )
            logLine("CreateRenderNodeClipOptionFromRoundRectShape roundRect=$roundRectShape clip=$clipFromRoundRect")

            val circleShape =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateCircleShapeOption()
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetCircleShapeOptionCenterX(circleShape, 50.0f)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetCircleShapeOptionCenterY(circleShape, 50.0f)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetCircleShapeOptionRadius(circleShape, 25.0f)

            val maskFromCircle =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRenderNodeMaskOptionFromCircleShape(
                    circleShape
                )
            logLine("CreateRenderNodeMaskOptionFromCircleShape circle=$circleShape mask=$maskFromCircle")

            val clipFromCircle =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRenderNodeClipOptionFromCircleShape(
                    circleShape
                )
            logLine("CreateRenderNodeClipOptionFromCircleShape circle=$circleShape clip=$clipFromCircle")

            val ovalMask =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRenderNodeMaskOptionFromOvalShape(rectShape)
            logLine("CreateRenderNodeMaskOptionFromOvalShape rectShape=$rectShape mask=$ovalMask")

            val ovalClip =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRenderNodeClipOptionFromOvalShape(rectShape)
            logLine("CreateRenderNodeClipOptionFromOvalShape rectShape=$rectShape clip=$ovalClip")

            val commandPath =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateCommandPathOption()
            val commands = "M 0 0 L 10 10".cstr.ptr
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetCommandPathOptionCommands(commandPath, commands)

            val maskFromPath =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRenderNodeMaskOptionFromCommandPath(
                    commandPath
                )
            logLine("CreateRenderNodeMaskOptionFromCommandPath path=$commandPath mask=$maskFromPath")

            val clipFromPath =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRenderNodeClipOptionFromCommandPath(
                    commandPath
                )
            logLine("CreateRenderNodeClipOptionFromCommandPath path=$commandPath clip=$clipFromPath")

            // 释放资源
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeRenderNodeMaskOption(maskFromRect)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeRenderNodeMaskOption(maskFromRoundRect)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeRenderNodeMaskOption(maskFromCircle)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeRenderNodeMaskOption(ovalMask)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeRenderNodeClipOption(clipFromRect)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeRenderNodeClipOption(clipFromRoundRect)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeRenderNodeClipOption(clipFromCircle)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeRenderNodeClipOption(ovalClip)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeCommandPathOption(commandPath)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeRoundRectShapeOption(roundRectShape)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeCircleShapeOption(circleShape)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeRectShapeOption(rectShape)

            val setMarkNodeGroupResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetMarkNodeGroup(node, true)
            logLine("SetMarkNodeGroup result=$setMarkNodeGroupResult")

            val setDrawRegionResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetDrawRegion(
                    node,
                    0.0f,
                    0.0f,
                    100.0f,
                    200.0f
                )
            logLine("SetDrawRegion result=$setDrawRegionResult")

            val disposeResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNode(node)
        }
    }

    private fun testContentModifierInternal() {
        memScoped {
            val node = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateNode()

            val modifier = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateContentModifier()
            logLine("CreateContentModifier modifier=$modifier")

            val attachModifierResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_AttachContentModifier(node, modifier)
            logLine("AttachContentModifier result=$attachModifierResult")

            // 不设置真正的绘制回调，传 null 覆盖 API
            val setOnDrawResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetContentModifierOnDraw(
                    modifier,
                    null,
                    null
                )
            logLine("SetContentModifierOnDraw result=$setOnDrawResult")

            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeContentModifier(modifier)
            logLine("DisposeContentModifier done")

            val disposeNodeResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeNode(node)
        }
    }

    private fun testPropertyOperationsInternal() {
        memScoped {
            val floatProperty =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateFloatProperty(1.0f)
            val setFloatResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetFloatPropertyValue(floatProperty, 2.0f)
            val floatValue = alloc<FloatVar>()
            val getFloatResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetFloatPropertyValue(floatProperty, floatValue.ptr)
            logLine("FloatProperty prop=$floatProperty setResult=$setFloatResult getResult=$getFloatResult value=${floatValue.value}")

            val vector2Property =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateVector2Property(1.0f, 2.0f)
            val setVector2Result =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetVector2PropertyValue(vector2Property, 3.0f, 4.0f)
            val vec2X = alloc<FloatVar>()
            val vec2Y = alloc<FloatVar>()
            val getVector2Result =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetVector2PropertyValue(vector2Property, vec2X.ptr, vec2Y.ptr)
            logLine("Vector2Property prop=$vector2Property setResult=$setVector2Result getResult=$getVector2Result x=${vec2X.value} y=${vec2Y.value}")

            val colorProperty =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateColorProperty(0xFF0000FFu)
            val setColorResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetColorPropertyValue(colorProperty, 0x00FF00FFu)
            val colorValue = alloc<UIntVar>()
            val getColorResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetColorPropertyValue(colorProperty, colorValue.ptr)
            logLine("ColorProperty prop=$colorProperty setResult=$setColorResult getResult=$getColorResult value=0x${colorValue.value.toString(16)}")

            // Attach 非动画属性到 content modifier 上，覆盖 AttachFloat/Vector2/ColorProperty
            val modifier =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateContentModifier()

            val attachFloatResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_AttachFloatProperty(modifier, floatProperty)
            val attachVector2Result =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_AttachVector2Property(modifier, vector2Property)
            val attachColorResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_AttachColorProperty(modifier, colorProperty)
            logLine("AttachFloatProperty result=$attachFloatResult AttachVector2Property result=$attachVector2Result AttachColorProperty result=$attachColorResult")

            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeContentModifier(modifier)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeFloatProperty(floatProperty)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeVector2Property(vector2Property)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeColorProperty(colorProperty)
        }
    }

    private fun testAnimatablePropertyOperationsInternal() {
        memScoped {
            val modifier =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateContentModifier()

            // Float animatable
            val floatAnim =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateFloatAnimatableProperty(1.0f)
            val setFloatAnimResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetFloatAnimatablePropertyValue(floatAnim, 2.0f)
            val floatAnimValue = alloc<FloatVar>()
            val getFloatAnimResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetFloatAnimatablePropertyValue(floatAnim, floatAnimValue.ptr)
            logLine("FloatAnimatableProperty prop=$floatAnim setResult=$setFloatAnimResult getResult=$getFloatAnimResult value=${floatAnimValue.value}")

            val attachFloatAnimResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_AttachFloatAnimatableProperty(modifier, floatAnim)

            // Vector2 animatable
            val vector2Anim =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateVector2AnimatableProperty(1.0f, 2.0f)
            val setVector2AnimResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetVector2AnimatablePropertyValue(vector2Anim, 3.0f, 4.0f)
            val vec2AnimX = alloc<FloatVar>()
            val vec2AnimY = alloc<FloatVar>()
            val getVector2AnimResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetVector2AnimatablePropertyValue(vector2Anim, vec2AnimX.ptr, vec2AnimY.ptr)
            logLine("Vector2AnimatableProperty prop=$vector2Anim setResult=$setVector2AnimResult getResult=$getVector2AnimResult x=${vec2AnimX.value} y=${vec2AnimY.value}")

            val attachVector2AnimResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_AttachVector2AnimatableProperty(modifier, vector2Anim)

            // Color animatable
            val colorAnim =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateColorAnimatableProperty(0xFF0000FFu)
            val setColorAnimResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_SetColorAnimatablePropertyValue(colorAnim, 0x00FF00FFu)
            val colorAnimValue = alloc<UIntVar>()
            val getColorAnimResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetColorAnimatablePropertyValue(colorAnim, colorAnimValue.ptr)
            logLine("ColorAnimatableProperty prop=$colorAnim setResult=$setColorAnimResult getResult=$getColorAnimResult value=0x${colorAnimValue.value.toString(16)}")

            val attachColorAnimResult =
                platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_AttachColorAnimatableProperty(modifier, colorAnim)

            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeFloatAnimatableProperty(floatAnim)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeVector2AnimatableProperty(vector2Anim)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeColorAnimatableProperty(colorAnim)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeContentModifier(modifier)
        }
    }

    private fun testShapeOptionsInternal() {
        memScoped {
            val rectShape = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRectShapeOption()
            logLine("CreateRectShapeOption rectShape=$rectShape")

            val circleShape = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateCircleShapeOption()
            logLine("CreateCircleShapeOption circleShape=$circleShape")

            val roundRectShape = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateRoundRectShapeOption()
            logLine("CreateRoundRectShapeOption roundRectShape=$roundRectShape")

            val commandPath = platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_CreateCommandPathOption()
            logLine("CreateCommandPathOption commandPath=$commandPath")

            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeRectShapeOption(rectShape)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeCircleShapeOption(circleShape)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeRoundRectShapeOption(roundRectShape)
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_DisposeCommandPathOption(commandPath)
        }
    }

    private fun testNodeHandleApisInternal() {
        // ArkUI_NodeHandle 相关接口，直接使用 null 覆盖一次调用场景。
        val addRenderNodeResult =
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_AddRenderNode(null, null)
        logLine("OH_ArkUI_RenderNodeUtils_AddRenderNode(null,null) result=$addRenderNodeResult")

        val removeRenderNodeResult =
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_RemoveRenderNode(null, null)
        logLine("OH_ArkUI_RenderNodeUtils_RemoveRenderNode(null,null) result=$removeRenderNodeResult")

        val clearRenderNodeChildrenResult =
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_ClearRenderNodeChildren(null)
        logLine("OH_ArkUI_RenderNodeUtils_ClearRenderNodeChildren(null) result=$clearRenderNodeChildrenResult")

        val invalidateResult =
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_Invalidate(null)
        logLine("OH_ArkUI_RenderNodeUtils_Invalidate(null) result=$invalidateResult")

        val getRenderNodeResult =
            platform.ArkUI.ArkUI_RenderNodeUtils.OH_ArkUI_RenderNodeUtils_GetRenderNode(null, null)
        logLine("OH_ArkUI_RenderNodeUtils_GetRenderNode(null,null) result=$getRenderNodeResult")
    }
}
