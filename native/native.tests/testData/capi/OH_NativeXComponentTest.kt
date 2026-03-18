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
import platform.ArkUI.OH_NativeXComponent.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OH_NativeXComponentTest {

    private fun logLine(msg: String) = println(msg)


    @Test
    fun testEnum_ArkUI_XComponent_ImageAnalyzerState() {
        assertEquals(ARKUI_XCOMPONENT_AI_ANALYSIS_FINISHED.toInt(), 0)
        assertEquals(ARKUI_XCOMPONENT_AI_ANALYSIS_DISABLED.toInt(), 110000)
        assertEquals(ARKUI_XCOMPONENT_AI_ANALYSIS_UNSUPPORTED.toInt(), 110001)
        assertEquals(ARKUI_XCOMPONENT_AI_ANALYSIS_ONGOING.toInt(), 110002)
        assertEquals(ARKUI_XCOMPONENT_AI_ANALYSIS_STOPPED.toInt(), 110003)
        logLine("ArkUI_XComponent_ImageAnalyzerState passed")
    }

    @Test
    fun testEnum_OH_NativeXComponent_TouchEventType() {
        assertEquals(OH_NATIVEXCOMPONENT_DOWN.toInt(), 0)
        assertEquals(OH_NATIVEXCOMPONENT_UP.toInt(), 1)
        assertEquals(OH_NATIVEXCOMPONENT_MOVE.toInt(), 2)
        assertEquals(OH_NATIVEXCOMPONENT_CANCEL.toInt(), 3)
        assertEquals(OH_NATIVEXCOMPONENT_UNKNOWN.toInt(), 4)
        logLine("OH_NativeXComponent_TouchEventType passed")
    }

    @Test
    fun testEnum_OH_NativeXComponent_TouchPointToolType() {
        assertEquals(OH_NATIVEXCOMPONENT_TOOL_TYPE_UNKNOWN.toInt(), 0)
        assertEquals(OH_NATIVEXCOMPONENT_TOOL_TYPE_FINGER.toInt(), 1)
        assertEquals(OH_NATIVEXCOMPONENT_TOOL_TYPE_PEN.toInt(), 2)
        assertEquals(OH_NATIVEXCOMPONENT_TOOL_TYPE_MOUSE.toInt(), 7)
        logLine("OH_NativeXComponent_TouchPointToolType passed")
    }

    @Test
    fun testEnum_OH_NativeXComponent_EventSourceType() {
        assertEquals(OH_NATIVEXCOMPONENT_SOURCE_TYPE_UNKNOWN.toInt(), 0)
        assertEquals(OH_NATIVEXCOMPONENT_SOURCE_TYPE_MOUSE.toInt(), 1)
        assertEquals(OH_NATIVEXCOMPONENT_SOURCE_TYPE_TOUCHSCREEN.toInt(), 2)
        assertEquals(OH_NATIVEXCOMPONENT_SOURCE_TYPE_KEYBOARD.toInt(), 5)
        logLine("OH_NativeXComponent_EventSourceType passed")
    }

    @Test
    fun testEnum_OH_NativeXComponent_MouseEventAction() {
        assertEquals(OH_NATIVEXCOMPONENT_MOUSE_NONE.toInt(), 0)
        assertEquals(OH_NATIVEXCOMPONENT_MOUSE_PRESS.toInt(), 1)
        assertEquals(OH_NATIVEXCOMPONENT_MOUSE_RELEASE.toInt(), 2)
        assertEquals(OH_NATIVEXCOMPONENT_MOUSE_MOVE.toInt(), 3)
        assertEquals(OH_NATIVEXCOMPONENT_MOUSE_CANCEL.toInt(), 4)
        logLine("OH_NativeXComponent_MouseEventAction passed")
    }

    @Test
    fun testEnum_OH_NativeXComponent_MouseEventButton() {
        assertEquals(OH_NATIVEXCOMPONENT_NONE_BUTTON.toInt(), 0)
        assertEquals(OH_NATIVEXCOMPONENT_LEFT_BUTTON.toInt(), 0x01)
        assertEquals(OH_NATIVEXCOMPONENT_RIGHT_BUTTON.toInt(), 0x02)
        assertEquals(OH_NATIVEXCOMPONENT_MIDDLE_BUTTON.toInt(), 0x04)
        logLine("OH_NativeXComponent_MouseEventButton passed")
    }

    @Test
    fun testEnum_OH_NativeXComponent_TouchEvent_SourceTool() {
        assertEquals(OH_NATIVEXCOMPONENT_SOURCETOOL_UNKNOWN.toInt(), 0)
        assertEquals(OH_NATIVEXCOMPONENT_SOURCETOOL_FINGER.toInt(), 1)
        assertEquals(OH_NATIVEXCOMPONENT_SOURCETOOL_PEN.toInt(), 2)
        assertEquals(OH_NATIVEXCOMPONENT_SOURCETOOL_MOUSE.toInt(), 7)
        logLine("OH_NativeXComponent_TouchEvent_SourceTool passed")
    }

    @Test
    fun testEnum_OH_NativeXComponent_KeyAction() {
        assertEquals(OH_NATIVEXCOMPONENT_KEY_ACTION_UNKNOWN.toInt(), -1)
        assertEquals(OH_NATIVEXCOMPONENT_KEY_ACTION_DOWN.toInt(), 0)
        assertEquals(OH_NATIVEXCOMPONENT_KEY_ACTION_UP.toInt(), 1)
        logLine("OH_NativeXComponent_KeyAction passed")
    }

    @Test
    fun testEnum_OH_NativeXComponent_KeyCode() {
        assertEquals(KEY_UNKNOWN.toInt(), -1)
        assertEquals(KEY_FN.toInt(), 0)
        assertEquals(KEY_HOME.toInt(), 1)
        assertEquals(KEY_BACK.toInt(), 2)
        assertEquals(KEY_MEDIA_PLAY_PAUSE.toInt(), 10)
        assertEquals(KEY_MEDIA_STOP.toInt(), 11)
        assertEquals(KEY_MEDIA_NEXT.toInt(), 12)
        assertEquals(KEY_MEDIA_PREVIOUS.toInt(), 13)
        assertEquals(KEY_MEDIA_REWIND.toInt(), 14)
        assertEquals(KEY_MEDIA_FAST_FORWARD.toInt(), 15)
        assertEquals(KEY_VOLUME_UP.toInt(), 16)
        assertEquals(KEY_VOLUME_DOWN.toInt(), 17)
        assertEquals(KEY_POWER.toInt(), 18)
        assertEquals(KEY_CAMERA.toInt(), 19)
        assertEquals(KEY_VOLUME_MUTE.toInt(), 22)
        assertEquals(KEY_MUTE.toInt(), 23)
        assertEquals(KEY_BRIGHTNESS_UP.toInt(), 40)
        assertEquals(KEY_BRIGHTNESS_DOWN.toInt(), 41)
        assertEquals(KEY_0.toInt(), 2000)
        assertEquals(KEY_1.toInt(), 2001)
        assertEquals(KEY_2.toInt(), 2002)
        assertEquals(KEY_3.toInt(), 2003)
        assertEquals(KEY_4.toInt(), 2004)
        assertEquals(KEY_5.toInt(), 2005)
        assertEquals(KEY_6.toInt(), 2006)
        assertEquals(KEY_7.toInt(), 2007)
        assertEquals(KEY_8.toInt(), 2008)
        assertEquals(KEY_9.toInt(), 2009)
        assertEquals(KEY_STAR.toInt(), 2010)
        assertEquals(KEY_POUND.toInt(), 2011)
        assertEquals(KEY_DPAD_UP.toInt(), 2012)
        assertEquals(KEY_DPAD_DOWN.toInt(), 2013)
        assertEquals(KEY_DPAD_LEFT.toInt(), 2014)
        assertEquals(KEY_DPAD_RIGHT.toInt(), 2015)
        assertEquals(KEY_DPAD_CENTER.toInt(), 2016)
        assertEquals(KEY_A.toInt(), 2017)
        assertEquals(KEY_Z.toInt(), 2042)
        assertEquals(KEY_COMMA.toInt(), 2043)
        assertEquals(KEY_PERIOD.toInt(), 2044)
        assertEquals(KEY_ALT_LEFT.toInt(), 2045)
        assertEquals(KEY_ALT_RIGHT.toInt(), 2046)
        assertEquals(KEY_SHIFT_LEFT.toInt(), 2047)
        assertEquals(KEY_SHIFT_RIGHT.toInt(), 2048)
        assertEquals(KEY_TAB.toInt(), 2049)
        assertEquals(KEY_SPACE.toInt(), 2050)
        assertEquals(KEY_SYM.toInt(), 2051)
        assertEquals(KEY_EXPLORER.toInt(), 2052)
        assertEquals(KEY_ENVELOPE.toInt(), 2053)
        assertEquals(KEY_ENTER.toInt(), 2054)
        assertEquals(KEY_DEL.toInt(), 2055)
        assertEquals(KEY_GRAVE.toInt(), 2056)
        assertEquals(KEY_MINUS.toInt(), 2057)
        assertEquals(KEY_EQUALS.toInt(), 2058)
        assertEquals(KEY_LEFT_BRACKET.toInt(), 2059)
        assertEquals(KEY_RIGHT_BRACKET.toInt(), 2060)
        assertEquals(KEY_BACKSLASH.toInt(), 2061)
        assertEquals(KEY_SEMICOLON.toInt(), 2062)
        assertEquals(KEY_APOSTROPHE.toInt(), 2063)
        assertEquals(KEY_SLASH.toInt(), 2064)
        assertEquals(KEY_AT.toInt(), 2065)
        assertEquals(KEY_PLUS.toInt(), 2066)
        assertEquals(KEY_MENU.toInt(), 2067)
        assertEquals(KEY_PAGE_UP.toInt(), 2068)
        assertEquals(KEY_PAGE_DOWN.toInt(), 2069)
        assertEquals(KEY_ESCAPE.toInt(), 2070)
        assertEquals(KEY_FORWARD_DEL.toInt(), 2071)
        assertEquals(KEY_CTRL_LEFT.toInt(), 2072)
        assertEquals(KEY_CTRL_RIGHT.toInt(), 2073)
        assertEquals(KEY_CAPS_LOCK.toInt(), 2074)
        assertEquals(KEY_SCROLL_LOCK.toInt(), 2075)
        assertEquals(KEY_META_LEFT.toInt(), 2076)
        assertEquals(KEY_META_RIGHT.toInt(), 2077)
        assertEquals(KEY_FUNCTION.toInt(), 2078)
        assertEquals(KEY_SYSRQ.toInt(), 2079)
        assertEquals(KEY_BREAK.toInt(), 2080)
        assertEquals(KEY_MOVE_HOME.toInt(), 2081)
        assertEquals(KEY_MOVE_END.toInt(), 2082)
        assertEquals(KEY_INSERT.toInt(), 2083)
        assertEquals(KEY_FORWARD.toInt(), 2084)
        assertEquals(KEY_MEDIA_PLAY.toInt(), 2085)
        assertEquals(KEY_MEDIA_PAUSE.toInt(), 2086)
        assertEquals(KEY_MEDIA_CLOSE.toInt(), 2087)
        assertEquals(KEY_MEDIA_EJECT.toInt(), 2088)
        assertEquals(KEY_MEDIA_RECORD.toInt(), 2089)
        assertEquals(KEY_F1.toInt(), 2090)
        assertEquals(KEY_F12.toInt(), 2101)
        assertEquals(KEY_NUM_LOCK.toInt(), 2102)
        assertEquals(KEY_NUMPAD_0.toInt(), 2103)
        assertEquals(KEY_NUMPAD_9.toInt(), 2112)
        assertEquals(KEY_NUMPAD_DIVIDE.toInt(), 2113)
        assertEquals(KEY_NUMPAD_MULTIPLY.toInt(), 2114)
        assertEquals(KEY_NUMPAD_SUBTRACT.toInt(), 2115)
        assertEquals(KEY_NUMPAD_ADD.toInt(), 2116)
        assertEquals(KEY_NUMPAD_DOT.toInt(), 2117)
        assertEquals(KEY_NUMPAD_COMMA.toInt(), 2118)
        assertEquals(KEY_NUMPAD_ENTER.toInt(), 2119)
        assertEquals(KEY_NUMPAD_EQUALS.toInt(), 2120)
        assertEquals(KEY_NUMPAD_LEFT_PAREN.toInt(), 2121)
        assertEquals(KEY_NUMPAD_RIGHT_PAREN.toInt(), 2122)
        assertEquals(KEY_VIRTUAL_MULTITASK.toInt(), 2210)
        assertEquals(KEY_SLEEP.toInt(), 2600)
        assertEquals(KEY_ZENKAKU_HANKAKU.toInt(), 2601)
        assertEquals(KEY_102ND.toInt(), 2602)
        assertEquals(KEY_RO.toInt(), 2603)
        assertEquals(KEY_KATAKANA.toInt(), 2604)
        assertEquals(KEY_HIRAGANA.toInt(), 2605)
        assertEquals(KEY_HENKAN.toInt(), 2606)
        assertEquals(KEY_KATAKANA_HIRAGANA.toInt(), 2607)
        assertEquals(KEY_MUHENKAN.toInt(), 2608)
        assertEquals(KEY_LINEFEED.toInt(), 2609)
        assertEquals(KEY_MACRO.toInt(), 2610)
        assertEquals(KEY_NUMPAD_PLUSMINUS.toInt(), 2611)
        assertEquals(KEY_SCALE.toInt(), 2612)
        assertEquals(KEY_HANGUEL.toInt(), 2613)
        assertEquals(KEY_HANJA.toInt(), 2614)
        assertEquals(KEY_YEN.toInt(), 2615)
        assertEquals(KEY_STOP.toInt(), 2616)
        assertEquals(KEY_AGAIN.toInt(), 2617)
        assertEquals(KEY_PROPS.toInt(), 2618)
        assertEquals(KEY_UNDO.toInt(), 2619)
        assertEquals(KEY_COPY.toInt(), 2620)
        assertEquals(KEY_OPEN.toInt(), 2621)
        assertEquals(KEY_PASTE.toInt(), 2622)
        assertEquals(KEY_FIND.toInt(), 2623)
        assertEquals(KEY_CUT.toInt(), 2624)
        assertEquals(KEY_HELP.toInt(), 2625)
        assertEquals(KEY_CALC.toInt(), 2626)
        assertEquals(KEY_FILE.toInt(), 2627)
        assertEquals(KEY_BOOKMARKS.toInt(), 2628)
        assertEquals(KEY_NEXT.toInt(), 2629)
        assertEquals(KEY_PLAYPAUSE.toInt(), 2630)
        assertEquals(KEY_PREVIOUS.toInt(), 2631)
        assertEquals(KEY_STOPCD.toInt(), 2632)
        assertEquals(KEY_CONFIG.toInt(), 2634)
        assertEquals(KEY_REFRESH.toInt(), 2635)
        assertEquals(KEY_EXIT.toInt(), 2636)
        assertEquals(KEY_EDIT.toInt(), 2637)
        assertEquals(KEY_SCROLLUP.toInt(), 2638)
        assertEquals(KEY_SCROLLDOWN.toInt(), 2639)
        assertEquals(KEY_NEW.toInt(), 2640)
        assertEquals(KEY_REDO.toInt(), 2641)
        assertEquals(KEY_CLOSE.toInt(), 2642)
        assertEquals(KEY_PLAY.toInt(), 2643)
        assertEquals(KEY_BASSBOOST.toInt(), 2644)
        assertEquals(KEY_PRINT.toInt(), 2645)
        assertEquals(KEY_CHAT.toInt(), 2646)
        assertEquals(KEY_FINANCE.toInt(), 2647)
        assertEquals(KEY_CANCEL.toInt(), 2648)
        assertEquals(KEY_KBDILLUM_TOGGLE.toInt(), 2649)
        assertEquals(KEY_KBDILLUM_DOWN.toInt(), 2650)
        assertEquals(KEY_KBDILLUM_UP.toInt(), 2651)
        assertEquals(KEY_SEND.toInt(), 2652)
        assertEquals(KEY_REPLY.toInt(), 2653)
        assertEquals(KEY_FORWARDMAIL.toInt(), 2654)
        assertEquals(KEY_SAVE.toInt(), 2655)
        assertEquals(KEY_DOCUMENTS.toInt(), 2656)
        assertEquals(KEY_VIDEO_NEXT.toInt(), 2657)
        assertEquals(KEY_VIDEO_PREV.toInt(), 2658)
        assertEquals(KEY_BRIGHTNESS_CYCLE.toInt(), 2659)
        assertEquals(KEY_BRIGHTNESS_ZERO.toInt(), 2660)
        assertEquals(KEY_DISPLAY_OFF.toInt(), 2661)
        assertEquals(KEY_BTN_MISC.toInt(), 2662)
        assertEquals(KEY_GOTO.toInt(), 2663)
        assertEquals(KEY_INFO.toInt(), 2664)
        assertEquals(KEY_PROGRAM.toInt(), 2665)
        assertEquals(KEY_PVR.toInt(), 2666)
        assertEquals(KEY_SUBTITLE.toInt(), 2667)
        assertEquals(KEY_FULL_SCREEN.toInt(), 2668)
        assertEquals(KEY_KEYBOARD.toInt(), 2669)
        assertEquals(KEY_ASPECT_RATIO.toInt(), 2670)
        assertEquals(KEY_PC.toInt(), 2671)
        assertEquals(KEY_TV.toInt(), 2672)
        assertEquals(KEY_TV2.toInt(), 2673)
        assertEquals(KEY_VCR.toInt(), 2674)
        assertEquals(KEY_VCR2.toInt(), 2675)
        assertEquals(KEY_SAT.toInt(), 2676)
        assertEquals(KEY_CD.toInt(), 2677)
        assertEquals(KEY_TAPE.toInt(), 2678)
        assertEquals(KEY_TUNER.toInt(), 2679)
        assertEquals(KEY_PLAYER.toInt(), 2680)
        assertEquals(KEY_DVD.toInt(), 2681)
        assertEquals(KEY_AUDIO.toInt(), 2682)
        assertEquals(KEY_VIDEO.toInt(), 2683)
        assertEquals(KEY_MEMO.toInt(), 2684)
        assertEquals(KEY_CALENDAR.toInt(), 2685)
        assertEquals(KEY_RED.toInt(), 2686)
        assertEquals(KEY_GREEN.toInt(), 2687)
        assertEquals(KEY_YELLOW.toInt(), 2688)
        assertEquals(KEY_BLUE.toInt(), 2689)
        assertEquals(KEY_CHANNELUP.toInt(), 2690)
        assertEquals(KEY_CHANNELDOWN.toInt(), 2691)
        assertEquals(KEY_LAST.toInt(), 2692)
        assertEquals(KEY_RESTART.toInt(), 2693)
        assertEquals(KEY_SLOW.toInt(), 2694)
        assertEquals(KEY_SHUFFLE.toInt(), 2695)
        assertEquals(KEY_VIDEOPHONE.toInt(), 2696)
        assertEquals(KEY_GAMES.toInt(), 2697)
        assertEquals(KEY_ZOOMIN.toInt(), 2698)
        assertEquals(KEY_ZOOMOUT.toInt(), 2699)
        assertEquals(KEY_ZOOMRESET.toInt(), 2700)
        assertEquals(KEY_WORDPROCESSOR.toInt(), 2701)
        assertEquals(KEY_EDITOR.toInt(), 2702)
        assertEquals(KEY_SPREADSHEET.toInt(), 2703)
        assertEquals(KEY_GRAPHICSEDITOR.toInt(), 2704)
        assertEquals(KEY_PRESENTATION.toInt(), 2705)
        assertEquals(KEY_DATABASE.toInt(), 2706)
        assertEquals(KEY_NEWS.toInt(), 2707)
        assertEquals(KEY_VOICEMAIL.toInt(), 2708)
        assertEquals(KEY_ADDRESSBOOK.toInt(), 2709)
        assertEquals(KEY_MESSENGER.toInt(), 2710)
        assertEquals(KEY_BRIGHTNESS_TOGGLE.toInt(), 2711)
        assertEquals(KEY_SPELLCHECK.toInt(), 2712)
        assertEquals(KEY_COFFEE.toInt(), 2713)
        assertEquals(KEY_MEDIA_REPEAT.toInt(), 2714)
        assertEquals(KEY_IMAGES.toInt(), 2715)
        assertEquals(KEY_BUTTONCONFIG.toInt(), 2716)
        assertEquals(KEY_TASKMANAGER.toInt(), 2717)
        assertEquals(KEY_JOURNAL.toInt(), 2718)
        assertEquals(KEY_CONTROLPANEL.toInt(), 2719)
        assertEquals(KEY_APPSELECT.toInt(), 2720)
        assertEquals(KEY_SCREENSAVER.toInt(), 2721)
        assertEquals(KEY_ASSISTANT.toInt(), 2722)
        assertEquals(KEY_KBD_LAYOUT_NEXT.toInt(), 2723)
        assertEquals(KEY_BRIGHTNESS_MIN.toInt(), 2724)
        assertEquals(KEY_BRIGHTNESS_MAX.toInt(), 2725)
        assertEquals(KEY_KBDINPUTASSIST_PREV.toInt(), 2726)
        assertEquals(KEY_KBDINPUTASSIST_NEXT.toInt(), 2727)
        assertEquals(KEY_KBDINPUTASSIST_PREVGROUP.toInt(), 2728)
        assertEquals(KEY_KBDINPUTASSIST_NEXTGROUP.toInt(), 2729)
        assertEquals(KEY_KBDINPUTASSIST_ACCEPT.toInt(), 2730)
        assertEquals(KEY_KBDINPUTASSIST_CANCEL.toInt(), 2731)
        assertEquals(KEY_FRONT.toInt(), 2800)
        assertEquals(KEY_SETUP.toInt(), 2801)
        assertEquals(KEY_WAKEUP.toInt(), 2802)
        assertEquals(KEY_SENDFILE.toInt(), 2803)
        assertEquals(KEY_DELETEFILE.toInt(), 2804)
        assertEquals(KEY_XFER.toInt(), 2805)
        assertEquals(KEY_PROG1.toInt(), 2806)
        assertEquals(KEY_PROG2.toInt(), 2807)
        assertEquals(KEY_MSDOS.toInt(), 2808)
        assertEquals(KEY_SCREENLOCK.toInt(), 2809)
        assertEquals(KEY_DIRECTION_ROTATE_DISPLAY.toInt(), 2810)
        assertEquals(KEY_CYCLEWINDOWS.toInt(), 2811)
        assertEquals(KEY_COMPUTER.toInt(), 2812)
        assertEquals(KEY_EJECTCLOSECD.toInt(), 2813)
        assertEquals(KEY_ISO.toInt(), 2814)
        assertEquals(KEY_MOVE.toInt(), 2815)
        assertEquals(KEY_F13.toInt(), 2816)
        assertEquals(KEY_F24.toInt(), 2827)
        assertEquals(KEY_PROG3.toInt(), 2828)
        assertEquals(KEY_PROG4.toInt(), 2829)
        assertEquals(KEY_DASHBOARD.toInt(), 2830)
        assertEquals(KEY_SUSPEND.toInt(), 2831)
        assertEquals(KEY_HP.toInt(), 2832)
        assertEquals(KEY_SOUND.toInt(), 2833)
        assertEquals(KEY_QUESTION.toInt(), 2834)
        assertEquals(KEY_CONNECT.toInt(), 2836)
        assertEquals(KEY_SPORT.toInt(), 2837)
        assertEquals(KEY_SHOP.toInt(), 2838)
        assertEquals(KEY_ALTERASE.toInt(), 2839)
        assertEquals(KEY_SWITCHVIDEOMODE.toInt(), 2841)
        assertEquals(KEY_BATTERY.toInt(), 2842)
        assertEquals(KEY_BLUETOOTH.toInt(), 2843)
        assertEquals(KEY_WLAN.toInt(), 2844)
        assertEquals(KEY_UWB.toInt(), 2845)
        assertEquals(KEY_WWAN_WIMAX.toInt(), 2846)
        assertEquals(KEY_RFKILL.toInt(), 2847)
        assertEquals(KEY_CHANNEL.toInt(), 3001)
        assertEquals(KEY_BTN_0.toInt(), 3100)
        assertEquals(KEY_BTN_9.toInt(), 3109)
        logLine("OH_NativeXComponent_KeyCode passed")
    }

    @Test
    fun testOH_NativeXComponent_GetNativeXComponent() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        logLine("OH_NativeXComponent_GetNativeXComponent(null) comp=$comp")
        assertTrue(comp == null)
    }

    @Test
    fun testOH_NativeXComponent_GetXComponentId() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val idBuf = ByteArray(129)
            idBuf.usePinned { pinned ->
                val sizeVar = alloc<ULongVar>()
                val retId = OH_NativeXComponent_GetXComponentId(comp, pinned.addressOf(0), sizeVar.ptr)
                logLine("OH_NativeXComponent_GetXComponentId ret=$retId size=${sizeVar.value}")
            }
        }
    }

    @Test
    fun testOH_NativeXComponent_GetXComponentSize() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val w = alloc<ULongVar>(); val h = alloc<ULongVar>()
            val retSize = OH_NativeXComponent_GetXComponentSize(comp, null, w.ptr, h.ptr)
            logLine("OH_NativeXComponent_GetXComponentSize ret=$retSize w=${w.value} h=${h.value}")
        }
    }

    @Test
    fun testOH_NativeXComponent_GetXComponentOffset() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val xOff = alloc<DoubleVar>(); val yOff = alloc<DoubleVar>()
            val retOffset = OH_NativeXComponent_GetXComponentOffset(comp, null, xOff.ptr, yOff.ptr)
            logLine("OH_NativeXComponent_GetXComponentOffset ret=$retOffset")
        }
    }

    @Test
    fun testOH_NativeXComponent_GetTouchEvent() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val touchEv = alloc<OH_NativeXComponent_TouchEvent>()
            val retTouch = OH_NativeXComponent_GetTouchEvent(comp, null, touchEv.ptr)
            logLine("OH_NativeXComponent_GetTouchEvent ret=$retTouch numPoints=${touchEv.numPoints}")
        }
    }

    @Test
    fun testOH_NativeXComponent_GetTouchPoint_Fields() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val toolType = alloc<UIntVar>()
            OH_NativeXComponent_GetTouchPointToolType(comp, 0u, toolType.ptr)
            val tiltX = alloc<FloatVar>(); OH_NativeXComponent_GetTouchPointTiltX(comp, 0u, tiltX.ptr)
            val tiltY = alloc<FloatVar>(); OH_NativeXComponent_GetTouchPointTiltY(comp, 0u, tiltY.ptr)
            val winX = alloc<FloatVar>(); OH_NativeXComponent_GetTouchPointWindowX(comp, 0u, winX.ptr)
            val winY = alloc<FloatVar>(); OH_NativeXComponent_GetTouchPointWindowY(comp, 0u, winY.ptr)
            val dispX = alloc<FloatVar>(); OH_NativeXComponent_GetTouchPointDisplayX(comp, 0u, dispX.ptr)
            val dispY = alloc<FloatVar>(); OH_NativeXComponent_GetTouchPointDisplayY(comp, 0u, dispY.ptr)
        }
    }

    @Test
    fun testOH_NativeXComponent_GetHistoricalPoints() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val histSize = alloc<IntVar>(); val histPtr = alloc<CPointerVar<OH_NativeXComponent_HistoricalPoint>>()
            val retHist = OH_NativeXComponent_GetHistoricalPoints(comp, null, histSize.ptr, histPtr.ptr)
            logLine("OH_NativeXComponent_GetHistoricalPoints ret=$retHist size=${histSize.value}")
        }
    }

    @Test
    fun testOH_NativeXComponent_GetMouseEvent() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val mouseEv = alloc<OH_NativeXComponent_MouseEvent>()
            val retMouse = OH_NativeXComponent_GetMouseEvent(comp, null, mouseEv.ptr)
            logLine("OH_NativeXComponent_GetMouseEvent ret=$retMouse")
        }
    }

    @Test
    fun testOH_NativeXComponent_RegisterCallback() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val cb = alloc<OH_NativeXComponent_Callback>()
            cb.OnSurfaceCreated = null; cb.OnSurfaceChanged = null; cb.OnSurfaceDestroyed = null; cb.DispatchTouchEvent = null
            val retRegCb = OH_NativeXComponent_RegisterCallback(comp, cb.ptr)
            logLine("OH_NativeXComponent_RegisterCallback ret=$retRegCb")
        }
    }

    @Test
    fun testOH_NativeXComponent_RegisterMouseEventCallback() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val mouseCb = alloc<OH_NativeXComponent_MouseEvent_Callback>()
            mouseCb.DispatchMouseEvent = null; mouseCb.DispatchHoverEvent = null
            val retMouseCb = OH_NativeXComponent_RegisterMouseEventCallback(comp, mouseCb.ptr)
            logLine("OH_NativeXComponent_RegisterMouseEventCallback ret=$retMouseCb")
        }
    }

    @Test
    fun testOH_NativeXComponent_GetExtraMouseEventInfo_GetMouseEventModifierKeyStates() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val extraMousePtr = alloc<CPointerVar<OH_NativeXComponent_ExtraMouseEventInfo>>()
            val retExtra = try { OH_NativeXComponent_GetExtraMouseEventInfo(comp, extraMousePtr.ptr) } catch (e: Throwable) { logLine("OH_NativeXComponent_GetExtraMouseEventInfo (API 20) exception: $e"); -1 }
            logLine("OH_NativeXComponent_GetExtraMouseEventInfo ret=$retExtra extra=${extraMousePtr.value}")
            val modKeys = alloc<ULongVar>()
            try { OH_NativeXComponent_GetMouseEventModifierKeyStates(extraMousePtr.value, modKeys.ptr) } catch (e: Throwable) { logLine("OH_NativeXComponent_GetMouseEventModifierKeyStates (API 20) exception: $e") }
        }
    }

    @Test
    fun testOH_NativeXComponent_RegisterFocusEventCallback() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        OH_NativeXComponent_RegisterFocusEventCallback(comp, null)
    }

    @Test
    fun testOH_NativeXComponent_RegisterKeyEventCallback() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        OH_NativeXComponent_RegisterKeyEventCallback(comp, null)
    }

    @Test
    fun testOH_NativeXComponent_RegisterBlurEventCallback() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        OH_NativeXComponent_RegisterBlurEventCallback(comp, null)
    }

    @Test
    fun testOH_NativeXComponent_GetKeyEvent_AndGetters() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val keyEvPtr = alloc<CPointerVar<OH_NativeXComponent_KeyEvent>>()
            val retKeyEv = OH_NativeXComponent_GetKeyEvent(comp, keyEvPtr.ptr)
            logLine("OH_NativeXComponent_GetKeyEvent ret=$retKeyEv keyEvent=${keyEvPtr.value}")
            val keyAction = alloc<IntVar>()
            OH_NativeXComponent_GetKeyEventAction(keyEvPtr.value, keyAction.ptr)
            val keyCode = alloc<IntVar>()
            OH_NativeXComponent_GetKeyEventCode(keyEvPtr.value, keyCode.ptr)
            val keySourceType = alloc<UIntVar>()
            OH_NativeXComponent_GetKeyEventSourceType(keyEvPtr.value, keySourceType.ptr)
            val keyDeviceId = alloc<LongVar>()
            OH_NativeXComponent_GetKeyEventDeviceId(keyEvPtr.value, keyDeviceId.ptr)
            val keyTs = alloc<LongVar>()
            OH_NativeXComponent_GetKeyEventTimestamp(keyEvPtr.value, keyTs.ptr)
            val modKeys = alloc<ULongVar>()
            try { OH_NativeXComponent_GetKeyEventModifierKeyStates(keyEvPtr.value, modKeys.ptr) } catch (e: Throwable) { logLine("OH_NativeXComponent_GetKeyEventModifierKeyStates (API 20) exception: $e") }
            val numLock = alloc<BooleanVar>()
            try { OH_NativeXComponent_GetKeyEventNumLockState(keyEvPtr.value, numLock.ptr) } catch (e: Throwable) { logLine("OH_NativeXComponent_GetKeyEventNumLockState (API 20) exception: $e") }
            val capsLock = alloc<BooleanVar>()
            try { OH_NativeXComponent_GetKeyEventCapsLockState(keyEvPtr.value, capsLock.ptr) } catch (e: Throwable) { logLine("OH_NativeXComponent_GetKeyEventCapsLockState (API 20) exception: $e") }
            val scrollLock = alloc<BooleanVar>()
            try { OH_NativeXComponent_GetKeyEventScrollLockState(keyEvPtr.value, scrollLock.ptr) } catch (e: Throwable) { logLine("OH_NativeXComponent_GetKeyEventScrollLockState (API 20) exception: $e") }
        }
    }

    @Test
    fun testOH_NativeXComponent_SetExpectedFrameRateRange() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val rateRange = alloc<OH_NativeXComponent_ExpectedRateRange>()
            rateRange.min = 0; rateRange.max = 60; rateRange.expected = 30
            val retRate = OH_NativeXComponent_SetExpectedFrameRateRange(comp, rateRange.ptr)
            logLine("OH_NativeXComponent_SetExpectedFrameRateRange ret=$retRate")
        }
    }

    @Test
    fun testOH_NativeXComponent_RegisterOnFrameCallback() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        val retOnFrame = OH_NativeXComponent_RegisterOnFrameCallback(comp, null)
        logLine("OH_NativeXComponent_RegisterOnFrameCallback ret=$retOnFrame")
    }

    @Test
    fun testOH_NativeXComponent_UnregisterOnFrameCallback() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        val retUnreg = OH_NativeXComponent_UnregisterOnFrameCallback(comp)
        logLine("OH_NativeXComponent_UnregisterOnFrameCallback ret=$retUnreg")
    }

    @Test
    fun testOH_NativeXComponent_AttachNativeRootNode() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        OH_NativeXComponent_AttachNativeRootNode(comp, null)
    }

    @Test
    fun testOH_NativeXComponent_DetachNativeRootNode() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        OH_NativeXComponent_DetachNativeRootNode(comp, null)
    }

    @Test
    fun testOH_NativeXComponent_RegisterSurfaceShowCallback() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        OH_NativeXComponent_RegisterSurfaceShowCallback(comp, null)
    }

    @Test
    fun testOH_NativeXComponent_RegisterSurfaceHideCallback() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        OH_NativeXComponent_RegisterSurfaceHideCallback(comp, null)
    }

    @Test
    fun testOH_NativeXComponent_RegisterUIInputEventCallback() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        OH_NativeXComponent_RegisterUIInputEventCallback(comp, null, 0u)
    }

    @Test
    fun testOH_NativeXComponent_SetNeedSoftKeyboard() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        OH_NativeXComponent_SetNeedSoftKeyboard(comp, false)
    }

    @Test
    fun testOH_NativeXComponent_RegisterOnTouchInterceptCallback() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        OH_NativeXComponent_RegisterOnTouchInterceptCallback(comp, null)
    }

    @Test
    fun testOH_NativeXComponent_GetTouchEventSourceType() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val srcType = alloc<UIntVar>()
            val retSrcType = OH_NativeXComponent_GetTouchEventSourceType(comp, 0, srcType.ptr)
            logLine("OH_NativeXComponent_GetTouchEventSourceType ret=$retSrcType sourceType=${srcType.value}")
        }
    }

    @Test
    fun testOH_NativeXComponent_GetNativeAccessibilityProvider() {
        memScoped {
            val comp = OH_NativeXComponent_GetNativeXComponent(null)
            val accProv = alloc<CPointerVar<ArkUI_AccessibilityProvider>>()
            val retAcc = OH_NativeXComponent_GetNativeAccessibilityProvider(comp, accProv.ptr)
            logLine("OH_NativeXComponent_GetNativeAccessibilityProvider ret=$retAcc provider=${accProv.value}")
        }
    }

    @Test
    fun testOH_NativeXComponent_RegisterKeyEventCallbackWithResult() {
        val comp = OH_NativeXComponent_GetNativeXComponent(null)
        val retKeyCbRes = OH_NativeXComponent_RegisterKeyEventCallbackWithResult(comp, null)
        logLine("OH_NativeXComponent_RegisterKeyEventCallbackWithResult ret=$retKeyCbRes")
    }

    @Test
    fun testOH_ArkUI_XComponent_StartImageAnalyzer() {
        val startRet = try { OH_ArkUI_XComponent_StartImageAnalyzer(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_XComponent_StartImageAnalyzer (API 18) exception: $e"); -1 }
        logLine("OH_ArkUI_XComponent_StartImageAnalyzer ret=$startRet")
    }

    @Test
    fun testOH_ArkUI_XComponent_StopImageAnalyzer() {
        val stopRet = try { OH_ArkUI_XComponent_StopImageAnalyzer(null) } catch (e: Throwable) { logLine("OH_ArkUI_XComponent_StopImageAnalyzer (API 18) exception: $e"); -1 }
        logLine("OH_ArkUI_XComponent_StopImageAnalyzer ret=$stopRet")
    }

    @Test
    fun testOH_ArkUI_SurfaceHolder_Create() {
        val holder = try { OH_ArkUI_SurfaceHolder_Create(null) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_Create (API 19) exception: $e"); null }
        logLine("OH_ArkUI_SurfaceHolder_Create holder=$holder")
        try { OH_ArkUI_SurfaceHolder_Dispose(holder) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_Dispose (API 19) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_SurfaceHolder_SetUserData_GetUserData() {
        val holder = try { OH_ArkUI_SurfaceHolder_Create(null) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_Create (API 19) exception: $e"); null }
        val retSetUser = try { OH_ArkUI_SurfaceHolder_SetUserData(holder, null) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_SetUserData (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_SurfaceHolder_SetUserData ret=$retSetUser")
        val userData = try { OH_ArkUI_SurfaceHolder_GetUserData(holder) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_GetUserData (API 19) exception: $e"); null }
        logLine("OH_ArkUI_SurfaceHolder_GetUserData ret=$userData")
        try { OH_ArkUI_SurfaceHolder_Dispose(holder) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_Dispose (API 19) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_SurfaceCallback_Create_AndSetEvents() {
        val callback = try { OH_ArkUI_SurfaceCallback_Create() } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceCallback_Create (API 19) exception: $e"); null }
        logLine("OH_ArkUI_SurfaceCallback_Create callback=$callback")
        try { OH_ArkUI_SurfaceCallback_SetSurfaceCreatedEvent(callback, null) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceCallback_SetSurfaceCreatedEvent (API 19) exception: $e") }
        try { OH_ArkUI_SurfaceCallback_SetSurfaceChangedEvent(callback, null) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceCallback_SetSurfaceChangedEvent (API 19) exception: $e") }
        try { OH_ArkUI_SurfaceCallback_SetSurfaceDestroyedEvent(callback, null) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceCallback_SetSurfaceDestroyedEvent (API 19) exception: $e") }
        try { OH_ArkUI_SurfaceCallback_SetSurfaceShowEvent(callback, null) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceCallback_SetSurfaceShowEvent (API 20) exception: $e") }
        try { OH_ArkUI_SurfaceCallback_SetSurfaceHideEvent(callback, null) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceCallback_SetSurfaceHideEvent (API 20) exception: $e") }
        try { OH_ArkUI_SurfaceCallback_Dispose(callback) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceCallback_Dispose (API 19) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_SurfaceHolder_AddSurfaceCallback_RemoveSurfaceCallback() {
        val holder = try { OH_ArkUI_SurfaceHolder_Create(null) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_Create (API 19) exception: $e"); null }
        val callback = try { OH_ArkUI_SurfaceCallback_Create() } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceCallback_Create (API 19) exception: $e"); null }
        val retAddCb = try { OH_ArkUI_SurfaceHolder_AddSurfaceCallback(holder, callback) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_AddSurfaceCallback (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_SurfaceHolder_AddSurfaceCallback ret=$retAddCb")
        val retRemoveCb = try { OH_ArkUI_SurfaceHolder_RemoveSurfaceCallback(holder, callback) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_RemoveSurfaceCallback (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_SurfaceHolder_RemoveSurfaceCallback ret=$retRemoveCb")
        try { OH_ArkUI_SurfaceCallback_Dispose(callback) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceCallback_Dispose (API 19) exception: $e") }
        try { OH_ArkUI_SurfaceHolder_Dispose(holder) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_Dispose (API 19) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_XComponent_GetNativeWindow() {
        val holder = try { OH_ArkUI_SurfaceHolder_Create(null) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_Create (API 19) exception: $e"); null }
        val nativeWin = try { OH_ArkUI_XComponent_GetNativeWindow(holder) } catch (e: Throwable) { logLine("OH_ArkUI_XComponent_GetNativeWindow (API 19) exception: $e"); null }
        logLine("OH_ArkUI_XComponent_GetNativeWindow ret=$nativeWin")
        try { OH_ArkUI_SurfaceHolder_Dispose(holder) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_Dispose (API 19) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_XComponentSurfaceConfig_Create_SetIsOpaque_Dispose() {
        val config = try { OH_ArkUI_XComponentSurfaceConfig_Create() } catch (e: Throwable) { logLine("OH_ArkUI_XComponentSurfaceConfig_Create (API 22) exception: $e"); null }
        logLine("OH_ArkUI_XComponentSurfaceConfig_Create config=$config")
        try { OH_ArkUI_XComponentSurfaceConfig_SetIsOpaque(config, true) } catch (e: Throwable) { logLine("OH_ArkUI_XComponentSurfaceConfig_SetIsOpaque (API 22) exception: $e") }
        try { OH_ArkUI_XComponentSurfaceConfig_Dispose(config) } catch (e: Throwable) { logLine("OH_ArkUI_XComponentSurfaceConfig_Dispose (API 22) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_SurfaceHolder_SetSurfaceConfig() {
        memScoped {
            val holder = try { OH_ArkUI_SurfaceHolder_Create(null) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_Create (API 19) exception: $e"); null }
            val config = try { OH_ArkUI_XComponentSurfaceConfig_Create() } catch (e: Throwable) { logLine("OH_ArkUI_XComponentSurfaceConfig_Create (API 22) exception: $e"); null }
            try { OH_ArkUI_XComponentSurfaceConfig_SetIsOpaque(config, true) } catch (e: Throwable) { logLine("OH_ArkUI_XComponentSurfaceConfig_SetIsOpaque (API 22) exception: $e") }
            val retSetConfig = try { OH_ArkUI_SurfaceHolder_SetSurfaceConfig(holder, config) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_SetSurfaceConfig (API 22) exception: $e"); -1 }
            logLine("OH_ArkUI_SurfaceHolder_SetSurfaceConfig ret=$retSetConfig")
            try { OH_ArkUI_XComponentSurfaceConfig_Dispose(config) } catch (e: Throwable) { logLine("OH_ArkUI_XComponentSurfaceConfig_Dispose (API 22) exception: $e") }
            try { OH_ArkUI_SurfaceHolder_Dispose(holder) } catch (e: Throwable) { logLine("OH_ArkUI_SurfaceHolder_Dispose (API 19) exception: $e") }
        }
    }

    @Test
    fun testOH_ArkUI_XComponent_SetAutoInitialize() {
        val retAutoInit = try { OH_ArkUI_XComponent_SetAutoInitialize(null, true) } catch (e: Throwable) { logLine("OH_ArkUI_XComponent_SetAutoInitialize (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_XComponent_SetAutoInitialize ret=$retAutoInit")
    }

    @Test
    fun testOH_ArkUI_XComponent_Initialize() {
        val retInit = try { OH_ArkUI_XComponent_Initialize(null) } catch (e: Throwable) { logLine("OH_ArkUI_XComponent_Initialize (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_XComponent_Initialize ret=$retInit")
    }

    @Test
    fun testOH_ArkUI_XComponent_Finalize() {
        val retFinal = try { OH_ArkUI_XComponent_Finalize(null) } catch (e: Throwable) { logLine("OH_ArkUI_XComponent_Finalize (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_XComponent_Finalize ret=$retFinal")
    }

    @Test
    fun testOH_ArkUI_XComponent_IsInitialized() {
        memScoped {
            val isInit = alloc<BooleanVar>()
            val retIsInit = try { OH_ArkUI_XComponent_IsInitialized(null, isInit.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_XComponent_IsInitialized (API 19) exception: $e"); -1 }
            logLine("OH_ArkUI_XComponent_IsInitialized ret=$retIsInit isInitialized=${isInit.value}")
        }
    }

    @Test
    fun testOH_ArkUI_XComponent_SetExpectedFrameRateRange() {
        memScoped {
            val range = alloc<OH_NativeXComponent_ExpectedRateRange>()
            range.min = 0; range.max = 60; range.expected = 30
            val retRange = try { OH_ArkUI_XComponent_SetExpectedFrameRateRange(null, range.readValue()) } catch (e: Throwable) { logLine("OH_ArkUI_XComponent_SetExpectedFrameRateRange (API 20) exception: $e"); -1 }
            logLine("OH_ArkUI_XComponent_SetExpectedFrameRateRange ret=$retRange")
        }
    }

    @Test
    fun testOH_ArkUI_XComponent_RegisterOnFrameCallback() {
        val retRegFrame = try { OH_ArkUI_XComponent_RegisterOnFrameCallback(null, null) } catch (e: Throwable) { logLine("OH_ArkUI_XComponent_RegisterOnFrameCallback (API 20) exception: $e"); -1 }
        logLine("OH_ArkUI_XComponent_RegisterOnFrameCallback ret=$retRegFrame")
    }

    @Test
    fun testOH_ArkUI_XComponent_UnregisterOnFrameCallback() {
        val retUnregFrame = try { OH_ArkUI_XComponent_UnregisterOnFrameCallback(null) } catch (e: Throwable) { logLine("OH_ArkUI_XComponent_UnregisterOnFrameCallback (API 20) exception: $e"); -1 }
        logLine("OH_ArkUI_XComponent_UnregisterOnFrameCallback ret=$retUnregFrame")
    }

    @Test
    fun testOH_ArkUI_XComponent_SetNeedSoftKeyboard() {
        val retSoftKb = try { OH_ArkUI_XComponent_SetNeedSoftKeyboard(null, false) } catch (e: Throwable) { logLine("OH_ArkUI_XComponent_SetNeedSoftKeyboard (API 20) exception: $e"); -1 }
        logLine("OH_ArkUI_XComponent_SetNeedSoftKeyboard ret=$retSoftKb")
    }

    @Test
    fun testOH_ArkUI_AccessibilityProvider_Create_Dispose() {
        val prov = try { OH_ArkUI_AccessibilityProvider_Create(null) } catch (e: Throwable) { logLine("OH_ArkUI_AccessibilityProvider_Create (API 20) exception: $e"); null }
        logLine("OH_ArkUI_AccessibilityProvider_Create prov=$prov")
        try { OH_ArkUI_AccessibilityProvider_Dispose(prov) } catch (e: Throwable) { logLine("OH_ArkUI_AccessibilityProvider_Dispose (API 20) exception: $e") }
    }
}
