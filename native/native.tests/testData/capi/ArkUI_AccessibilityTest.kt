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
import platform.ArkUI.ArkUI_Accessibility.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class ArkUI_AccessibilityTest {

    private fun logLine(message: String) {
        println("[stdout] ArkUI_AccessibilityTest $message")
    }

    @Test
    fun testEnums() {
        logLine("--- Testing ArkUI_Accessibility enums ---")

        val actInvalid = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_INVALID
        val actClick = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_CLICK
        val actLongClick = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_LONG_CLICK
        val actGain = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_GAIN_ACCESSIBILITY_FOCUS
        val actClear = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_CLEAR_ACCESSIBILITY_FOCUS
        val actScrollF = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_SCROLL_FORWARD
        val actScrollB = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_SCROLL_BACKWARD
        val actCopy = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_COPY
        val actPaste = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_PASTE
        val actCut = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_CUT
        val actSelect = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_SELECT_TEXT
        val actSetText = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_SET_TEXT
        val actSetCursor = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_SET_CURSOR_POSITION
        val actNextHtml = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_NEXT_HTML_ITEM
        val actPrevHtml = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_PREVIOUS_HTML_ITEM
        logLine("ActionType: INVALID=$actInvalid CLICK=$actClick LONG_CLICK=$actLongClick GAIN=$actGain CLEAR=$actClear SCROLL_F=$actScrollF SCROLL_B=$actScrollB COPY=$actCopy PASTE=$actPaste CUT=$actCut SELECT=$actSelect SET_TEXT=$actSetText SET_CURSOR=$actSetCursor NEXT_HTML=$actNextHtml PREV_HTML=$actPrevHtml")

        val evInvalid = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_INVALID
        val evClicked = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_CLICKED
        val evLongClicked = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_LONG_CLICKED
        val evSelected = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_SELECTED
        val evTextUpdate = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_TEXT_UPDATE
        val evPageState = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_PAGE_STATE_UPDATE
        val evPageContent = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_PAGE_CONTENT_UPDATE
        val evScrolled = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_SCROLLED
        val evAccFocused = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_ACCESSIBILITY_FOCUSED
        val evAccCleared = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_ACCESSIBILITY_FOCUS_CLEARED
        val evRequestFocus = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_REQUEST_ACCESSIBILITY_FOCUS
        val evPageOpen = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_PAGE_OPEN
        val evPageClose = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_PAGE_CLOSE
        val evAnnounce = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_ANNOUNCE_FOR_ACCESSIBILITY
        val evFocusNode = ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_FOCUS_NODE_UPDATE
        logLine("EventType: INVALID=$evInvalid CLICKED=$evClicked LONG_CLICKED=$evLongClicked SELECTED=$evSelected TEXT_UPDATE=$evTextUpdate PAGE_STATE=$evPageState PAGE_CONTENT=$evPageContent SCROLLED=$evScrolled ACC_FOCUSED=$evAccFocused ACC_CLEARED=$evAccCleared REQUEST_FOCUS=$evRequestFocus PAGE_OPEN=$evPageOpen PAGE_CLOSE=$evPageClose ANNOUNCE=$evAnnounce FOCUS_NODE=$evFocusNode")

        val ok = ARKUI_ACCESSIBILITY_NATIVE_RESULT_SUCCESSFUL
        val fail = ARKUI_ACCESSIBILITY_NATIVE_RESULT_FAILED
        val badParam = ARKUI_ACCESSIBILITY_NATIVE_RESULT_BAD_PARAMETER
        val oom = ARKUI_ACCESSIBILITY_NATIVE_RESULT_OUT_OF_MEMORY
        logLine("ErrorCode: SUCCESS=$ok FAILED=$fail BAD_PARAMETER=$badParam OUT_OF_MEMORY=$oom")

        val smCurrent = ARKUI_ACCESSIBILITY_NATIVE_SEARCH_MODE_PREFETCH_CURRENT
        val smPred = ARKUI_ACCESSIBILITY_NATIVE_SEARCH_MODE_PREFETCH_PREDECESSORS
        val smSiblings = ARKUI_ACCESSIBILITY_NATIVE_SEARCH_MODE_PREFETCH_SIBLINGS
        val smChildren = ARKUI_ACCESSIBILITY_NATIVE_SEARCH_MODE_PREFETCH_CHILDREN
        val smRecursive = ARKUI_ACCESSIBILITY_NATIVE_SEARCH_MODE_PREFETCH_RECURSIVE_CHILDREN
        logLine("SearchMode: CURRENT=$smCurrent PREDECESSORS=$smPred SIBLINGS=$smSiblings CHILDREN=$smChildren RECURSIVE_CHILDREN=$smRecursive")

        val ftInvalid = ARKUI_ACCESSIBILITY_NATIVE_FOCUS_TYPE_INVALID
        val ftInput = ARKUI_ACCESSIBILITY_NATIVE_FOCUS_TYPE_INPUT
        val ftAccessibility = ARKUI_ACCESSIBILITY_NATIVE_FOCUS_TYPE_ACCESSIBILITY
        logLine("FocusType: INVALID=$ftInvalid INPUT=$ftInput ACCESSIBILITY=$ftAccessibility")

        val dirInvalid = ARKUI_ACCESSIBILITY_NATIVE_DIRECTION_INVALID
        val dirUp = ARKUI_ACCESSIBILITY_NATIVE_DIRECTION_UP
        val dirDown = ARKUI_ACCESSIBILITY_NATIVE_DIRECTION_DOWN
        val dirLeft = ARKUI_ACCESSIBILITY_NATIVE_DIRECTION_LEFT
        val dirRight = ARKUI_ACCESSIBILITY_NATIVE_DIRECTION_RIGHT
        val dirForward = ARKUI_ACCESSIBILITY_NATIVE_DIRECTION_FORWARD
        val dirBackward = ARKUI_ACCESSIBILITY_NATIVE_DIRECTION_BACKWARD
        logLine("FocusMoveDirection: INVALID=$dirInvalid UP=$dirUp DOWN=$dirDown LEFT=$dirLeft RIGHT=$dirRight FORWARD=$dirForward BACKWARD=$dirBackward")
    }

    @Test
    fun testOH_ArkUI_CreateAccessibilityElementInfo() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        assertNotNull(elementInfo)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_CreateAccessibilityElementInfo passed")
    }

    @Test
    fun testOH_ArkUI_DestoryAccessibilityElementInfo() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_DestoryAccessibilityElementInfo passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetElementId() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetElementId(elementInfo, 1)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetElementId passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetParentId() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetParentId(elementInfo, 0)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetParentId passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetComponentType() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetComponentType(elementInfo, "Button")
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetComponentType passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetContents() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetContents(elementInfo, "Click me")
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetContents passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetHintText() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetHintText(elementInfo, "hint")
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetHintText passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetAccessibilityText() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetAccessibilityText(elementInfo, "acc text")
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetAccessibilityText passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetAccessibilityDescription() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetAccessibilityDescription(elementInfo, "desc")
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetAccessibilityDescription passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetChildNodeIds() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetChildNodeIds(elementInfo, 0, null)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetChildNodeIds passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetOperationActions() {
        memScoped {
            val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
            val action = alloc<ArkUI_AccessibleAction>().apply {
                actionType = ARKUI_ACCESSIBILITY_NATIVE_ACTION_TYPE_CLICK
                description = null
            }
            val r = OH_ArkUI_AccessibilityElementInfoSetOperationActions(elementInfo, 1, action.ptr)
            assertNotNull(r)
            OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        }
        logLine("OH_ArkUI_AccessibilityElementInfoSetOperationActions passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetScreenRect() {
        memScoped {
            val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
            val rect = alloc<ArkUI_AccessibleRect>().apply {
                leftTopX = 0
                leftTopY = 0
                rightBottomX = 100
                rightBottomY = 100
            }
            val r = OH_ArkUI_AccessibilityElementInfoSetScreenRect(elementInfo, rect.ptr)
            assertNotNull(r)
            OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        }
        logLine("OH_ArkUI_AccessibilityElementInfoSetScreenRect passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetCheckable() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetCheckable(elementInfo, false)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetCheckable passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetChecked() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetChecked(elementInfo, false)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetChecked passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetFocusable() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetFocusable(elementInfo, true)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetFocusable passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetFocused() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetFocused(elementInfo, false)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetFocused passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetVisible() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetVisible(elementInfo, true)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetVisible passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetAccessibilityFocused() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetAccessibilityFocused(elementInfo, false)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetAccessibilityFocused passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetSelected() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetSelected(elementInfo, false)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetSelected passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetClickable() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetClickable(elementInfo, true)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetClickable passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetLongClickable() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetLongClickable(elementInfo, false)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetLongClickable passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetEnabled() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetEnabled(elementInfo, true)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetEnabled passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetIsPassword() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetIsPassword(elementInfo, false)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetIsPassword passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetScrollable() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetScrollable(elementInfo, false)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetScrollable passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetEditable() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetEditable(elementInfo, false)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetEditable passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetIsHint() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetIsHint(elementInfo, false)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetIsHint passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetRangeInfo() {
        memScoped {
            val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
            val rangeInfo = alloc<ArkUI_AccessibleRangeInfo>().apply {
                min = 0.0
                max = 100.0
                current = 50.0
            }
            val r = OH_ArkUI_AccessibilityElementInfoSetRangeInfo(elementInfo, rangeInfo.ptr)
            assertNotNull(r)
            OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        }
        logLine("OH_ArkUI_AccessibilityElementInfoSetRangeInfo passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetGridInfo() {
        memScoped {
            val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
            val gridInfo = alloc<ArkUI_AccessibleGridInfo>().apply {
                rowCount = 2
                columnCount = 3
                selectionMode = 0
            }
            val r = OH_ArkUI_AccessibilityElementInfoSetGridInfo(elementInfo, gridInfo.ptr)
            assertNotNull(r)
            OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        }
        logLine("OH_ArkUI_AccessibilityElementInfoSetGridInfo passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetGridItemInfo() {
        memScoped {
            val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
            val gridItem = alloc<ArkUI_AccessibleGridItemInfo>().apply {
                heading = false
                selected = false
                columnIndex = 0
                rowIndex = 0
                columnSpan = 1
                rowSpan = 1
            }
            val r = OH_ArkUI_AccessibilityElementInfoSetGridItemInfo(elementInfo, gridItem.ptr)
            assertNotNull(r)
            OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        }
        logLine("OH_ArkUI_AccessibilityElementInfoSetGridItemInfo passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetSelectedTextStart() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetSelectedTextStart(elementInfo, 0)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetSelectedTextStart passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetSelectedTextEnd() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetSelectedTextEnd(elementInfo, 0)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetSelectedTextEnd passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetCurrentItemIndex() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetCurrentItemIndex(elementInfo, 0)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetCurrentItemIndex passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetStartItemIndex() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetStartItemIndex(elementInfo, 0)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetStartItemIndex passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetEndItemIndex() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetEndItemIndex(elementInfo, 0)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetEndItemIndex passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetItemCount() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetItemCount(elementInfo, 0)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetItemCount passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetAccessibilityOffset() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetAccessibilityOffset(elementInfo, 0)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetAccessibilityOffset passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetAccessibilityGroup() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetAccessibilityGroup(elementInfo, false)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetAccessibilityGroup passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetAccessibilityLevel() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetAccessibilityLevel(elementInfo, "default")
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetAccessibilityLevel passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetZIndex() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetZIndex(elementInfo, 0)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetZIndex passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetAccessibilityOpacity() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetAccessibilityOpacity(elementInfo, 1.0f)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetAccessibilityOpacity passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetBackgroundColor() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetBackgroundColor(elementInfo, "#FFFFFFFF")
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetBackgroundColor passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetBackgroundImage() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetBackgroundImage(elementInfo, "")
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetBackgroundImage passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetBlur() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetBlur(elementInfo, "")
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetBlur passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityElementInfoSetHitTestBehavior() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val r = OH_ArkUI_AccessibilityElementInfoSetHitTestBehavior(elementInfo, "default")
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityElementInfoSetHitTestBehavior passed")
    }

    @Test
    fun testOH_ArkUI_CreateAccessibilityEventInfo() {
        val eventInfo = OH_ArkUI_CreateAccessibilityEventInfo()
        assertNotNull(eventInfo)
        OH_ArkUI_DestoryAccessibilityEventInfo(eventInfo)
        logLine("OH_ArkUI_CreateAccessibilityEventInfo passed")
    }

    @Test
    fun testOH_ArkUI_DestoryAccessibilityEventInfo() {
        val eventInfo = OH_ArkUI_CreateAccessibilityEventInfo()
        OH_ArkUI_DestoryAccessibilityEventInfo(eventInfo)
        logLine("OH_ArkUI_DestoryAccessibilityEventInfo passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityEventSetEventType() {
        val eventInfo = OH_ArkUI_CreateAccessibilityEventInfo()
        val r = OH_ArkUI_AccessibilityEventSetEventType(eventInfo, ARKUI_ACCESSIBILITY_NATIVE_EVENT_TYPE_CLICKED)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityEventInfo(eventInfo)
        logLine("OH_ArkUI_AccessibilityEventSetEventType passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityEventSetTextAnnouncedForAccessibility() {
        val eventInfo = OH_ArkUI_CreateAccessibilityEventInfo()
        val r = OH_ArkUI_AccessibilityEventSetTextAnnouncedForAccessibility(eventInfo, "announced")
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityEventInfo(eventInfo)
        logLine("OH_ArkUI_AccessibilityEventSetTextAnnouncedForAccessibility passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityEventSetRequestFocusId() {
        val eventInfo = OH_ArkUI_CreateAccessibilityEventInfo()
        val r = OH_ArkUI_AccessibilityEventSetRequestFocusId(eventInfo, 0)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityEventInfo(eventInfo)
        logLine("OH_ArkUI_AccessibilityEventSetRequestFocusId passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityEventSetElementInfo() {
        val elementInfo = OH_ArkUI_CreateAccessibilityElementInfo()
        val eventInfo = OH_ArkUI_CreateAccessibilityEventInfo()
        val r = OH_ArkUI_AccessibilityEventSetElementInfo(eventInfo, elementInfo)
        assertNotNull(r)
        OH_ArkUI_DestoryAccessibilityEventInfo(eventInfo)
        OH_ArkUI_DestoryAccessibilityElementInfo(elementInfo)
        logLine("OH_ArkUI_AccessibilityEventSetElementInfo passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityProviderRegisterCallback() {
        val r = OH_ArkUI_AccessibilityProviderRegisterCallback(null, null)
        assertNotNull(r)
        logLine("OH_ArkUI_AccessibilityProviderRegisterCallback=$r")
        logLine("OH_ArkUI_AccessibilityProviderRegisterCallback passed")
    }

    @Test
    fun testOH_ArkUI_AccessibilityProviderRegisterCallbackWithInstance() {
        val r = OH_ArkUI_AccessibilityProviderRegisterCallbackWithInstance("", null, null)
        assertNotNull(r)
        logLine("OH_ArkUI_AccessibilityProviderRegisterCallbackWithInstance=$r")
        logLine("OH_ArkUI_AccessibilityProviderRegisterCallbackWithInstance passed")
    }

    @Test
    fun testOH_ArkUI_SendAccessibilityAsyncEvent() {
        OH_ArkUI_SendAccessibilityAsyncEvent(null, null, staticCFunction<Int, Unit> { _ -> })
        logLine("OH_ArkUI_SendAccessibilityAsyncEvent passed")
    }

    // @Test
    // fun testOH_ArkUI_AddAndGetAccessibilityElementInfo() {
    //     memScoped {
    //         val outElement = OH_ArkUI_AddAndGetAccessibilityElementInfo(null)
    //         assertNotNull(outElement)
    //         logLine("OH_ArkUI_AddAndGetAccessibilityElementInfo=$outElement")
    //     }
    //     logLine("OH_ArkUI_AddAndGetAccessibilityElementInfo passed")
    // }

    @Test
    fun testOH_ArkUI_FindAccessibilityActionArgumentByKey() {
        memScoped {
            val outValue = alloc<CPointerVar<ByteVar>>()
            val r = OH_ArkUI_FindAccessibilityActionArgumentByKey(null, "key", outValue.ptr)
            assertNotNull(r)
            logLine("OH_ArkUI_FindAccessibilityActionArgumentByKey=$r")
        }
        logLine("OH_ArkUI_FindAccessibilityActionArgumentByKey passed")
    }
}
