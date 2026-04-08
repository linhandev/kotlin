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
import platform.GameControllerKit.GameController.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class GameControllerTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_GameController_ErrorCode() {
        assertEquals(GAME_CONTROLLER_SUCCESS.toInt(), 0)
        assertEquals(GAME_CONTROLLER_PARAM_ERROR.toInt(), 401)
        assertEquals(GAME_CONTROLLER_MULTIMODAL_INPUT_ERROR.toInt(), 32200001)
        assertEquals(GAME_CONTROLLER_NO_MEMORY.toInt(), 32200002)
        logLine("testEnum_GameController_ErrorCode passed")
    }

    @Test
    fun testEnum_GameDevice_StatusChangedType() {
        assertEquals(OFFLINE.toInt(), 0)
        assertEquals(ONLINE.toInt(), 1)
        logLine("testEnum_GameDevice_StatusChangedType passed")
    }

    @Test
    fun testEnum_GameDevice_DeviceType() {
        assertEquals(UNKNOWN.toInt(), 0)
        assertEquals(GAME_PAD.toInt(), 1)
        logLine("testEnum_GameDevice_DeviceType passed")
    }

    @Test
    fun testEnum_GamePad_AxisSourceType() {
        assertEquals(DPAD.toInt(), 0)
        assertEquals(LEFT_THUMBSTICK.toInt(), 1)
        assertEquals(RIGHT_THUMBSTICK.toInt(), 2)
        assertEquals(LEFT_TRIGGER.toInt(), 3)
        assertEquals(RIGHT_TRIGGER.toInt(), 4)
        logLine("testEnum_GamePad_AxisSourceType passed")
    }

    @Test
    fun testEnum_GamePad_Button_ActionType() {
        assertEquals(DOWN.toInt(), 0)
        assertEquals(UP.toInt(), 1)
        logLine("testEnum_GamePad_Button_ActionType passed")
    }

    @Test
    fun testOH_GameDevice_GetAllDeviceInfos() {
        memScoped {
            val allDeviceInfos = alloc<CPointerVar<GameDevice_AllDeviceInfos>>()
            val ret = try { OH_GameDevice_GetAllDeviceInfos(allDeviceInfos.ptr) } catch (e: Throwable) { logLine("OH_GameDevice_GetAllDeviceInfos (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GameDevice_GetAllDeviceInfos ret=$ret")
        }
    }

    @Test
    fun testOH_GameDevice_RegisterDeviceMonitor() {
        val ret = try { OH_GameDevice_RegisterDeviceMonitor(null) } catch (e: Throwable) { logLine("OH_GameDevice_RegisterDeviceMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GameDevice_RegisterDeviceMonitor(null) ret=$ret")
    }

    @Test
    fun testOH_GameDevice_UnregisterDeviceMonitor() {
        val ret = try { OH_GameDevice_UnregisterDeviceMonitor() } catch (e: Throwable) { logLine("OH_GameDevice_UnregisterDeviceMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GameDevice_UnregisterDeviceMonitor ret=$ret")
    }

    @Test
    fun testOH_GameDevice_DestroyAllDeviceInfos() {
        memScoped {
            val allDeviceInfos = alloc<CPointerVar<GameDevice_AllDeviceInfos>>()
            allDeviceInfos.value = null
            val ret = try { OH_GameDevice_DestroyAllDeviceInfos(allDeviceInfos.ptr) } catch (e: Throwable) { logLine("OH_GameDevice_DestroyAllDeviceInfos (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GameDevice_DestroyAllDeviceInfos ret=$ret")
        }
    }

    @Test
    fun testOH_GameDevice_AllDeviceInfos_GetCount() {
        memScoped {
            val count = alloc<IntVar>()
            val ret = try { OH_GameDevice_AllDeviceInfos_GetCount(null, count.ptr) } catch (e: Throwable) { logLine("OH_GameDevice_AllDeviceInfos_GetCount (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GameDevice_AllDeviceInfos_GetCount ret=$ret")
        }
    }

    @Test
    fun testOH_GameDevice_AllDeviceInfos_GetDeviceInfo() {
        memScoped {
            val deviceInfo = alloc<CPointerVar<GameDevice_DeviceInfo>>()
            val ret = try { OH_GameDevice_AllDeviceInfos_GetDeviceInfo(null, 0, deviceInfo.ptr) } catch (e: Throwable) { logLine("OH_GameDevice_AllDeviceInfos_GetDeviceInfo (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GameDevice_AllDeviceInfos_GetDeviceInfo ret=$ret")
        }
    }

    @Test
    fun testOH_GameDevice_DeviceEvent_GetChangedType() {
        memScoped {
            val statusChangedType = alloc<UIntVar>()
            val ret = try { OH_GameDevice_DeviceEvent_GetChangedType(null, statusChangedType.ptr) } catch (e: Throwable) { logLine("OH_GameDevice_DeviceEvent_GetChangedType (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GameDevice_DeviceEvent_GetChangedType ret=$ret")
        }
    }

    @Test
    fun testOH_GameDevice_DeviceEvent_GetDeviceInfo() {
        memScoped {
            val deviceInfo = alloc<CPointerVar<GameDevice_DeviceInfo>>()
            val ret = try { OH_GameDevice_DeviceEvent_GetDeviceInfo(null, deviceInfo.ptr) } catch (e: Throwable) { logLine("OH_GameDevice_DeviceEvent_GetDeviceInfo (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GameDevice_DeviceEvent_GetDeviceInfo ret=$ret")
        }
    }

    @Test
    fun testOH_GameDevice_DestroyDeviceInfo() {
        memScoped {
            val deviceInfo = alloc<CPointerVar<GameDevice_DeviceInfo>>()
            deviceInfo.value = null
            val ret = try { OH_GameDevice_DestroyDeviceInfo(deviceInfo.ptr) } catch (e: Throwable) { logLine("OH_GameDevice_DestroyDeviceInfo (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GameDevice_DestroyDeviceInfo ret=$ret")
        }
    }

    @Test
    fun testOH_GameDevice_DeviceInfo_GetDeviceId() {
        memScoped {
            val deviceId = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_GameDevice_DeviceInfo_GetDeviceId(null, deviceId.ptr) } catch (e: Throwable) { logLine("OH_GameDevice_DeviceInfo_GetDeviceId (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GameDevice_DeviceInfo_GetDeviceId ret=$ret")
        }
    }

    @Test
    fun testOH_GameDevice_DeviceInfo_GetName() {
        memScoped {
            val name = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_GameDevice_DeviceInfo_GetName(null, name.ptr) } catch (e: Throwable) { logLine("OH_GameDevice_DeviceInfo_GetName (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GameDevice_DeviceInfo_GetName ret=$ret")
        }
    }

    @Test
    fun testOH_GameDevice_DeviceInfo_GetProduct() {
        memScoped {
            val product = alloc<IntVar>()
            val ret = try { OH_GameDevice_DeviceInfo_GetProduct(null, product.ptr) } catch (e: Throwable) { logLine("OH_GameDevice_DeviceInfo_GetProduct (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GameDevice_DeviceInfo_GetProduct ret=$ret")
        }
    }

    @Test
    fun testOH_GameDevice_DeviceInfo_GetVersion() {
        memScoped {
            val version = alloc<IntVar>()
            val ret = try { OH_GameDevice_DeviceInfo_GetVersion(null, version.ptr) } catch (e: Throwable) { logLine("OH_GameDevice_DeviceInfo_GetVersion (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GameDevice_DeviceInfo_GetVersion ret=$ret")
        }
    }

    @Test
    fun testOH_GameDevice_DeviceInfo_GetPhysicalAddress() {
        memScoped {
            val physicalAddress = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_GameDevice_DeviceInfo_GetPhysicalAddress(null, physicalAddress.ptr) } catch (e: Throwable) { logLine("OH_GameDevice_DeviceInfo_GetPhysicalAddress (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GameDevice_DeviceInfo_GetPhysicalAddress ret=$ret")
        }
    }

    @Test
    fun testOH_GameDevice_DeviceInfo_GetDeviceType() {
        memScoped {
            val deviceType = alloc<UIntVar>()
            val ret = try { OH_GameDevice_DeviceInfo_GetDeviceType(null, deviceType.ptr) } catch (e: Throwable) { logLine("OH_GameDevice_DeviceInfo_GetDeviceType (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GameDevice_DeviceInfo_GetDeviceType ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_LeftShoulder_RegisterButtonInputMonitor() {
        val ret = try { OH_GamePad_LeftShoulder_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_LeftShoulder_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_LeftShoulder_RegisterButtonInputMonitor(null) ret=$ret")
    }

    @Test
    fun testOH_GamePad_LeftShoulder_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_LeftShoulder_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_LeftShoulder_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_LeftShoulder_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_RightShoulder_RegisterButtonInputMonitor() {
        val ret = try { OH_GamePad_RightShoulder_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_RightShoulder_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_RightShoulder_RegisterButtonInputMonitor(null) ret=$ret")
    }

    @Test
    fun testOH_GamePad_RightShoulder_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_RightShoulder_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_RightShoulder_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_RightShoulder_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_LeftTrigger_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_LeftTrigger_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_LeftTrigger_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_LeftTrigger_RegisterAxisInputMonitor() {
        assertNotNull(try { OH_GamePad_LeftTrigger_RegisterAxisInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_LeftTrigger_RegisterAxisInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_RightTrigger_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_RightTrigger_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_RightTrigger_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_RightTrigger_RegisterAxisInputMonitor() {
        assertNotNull(try { OH_GamePad_RightTrigger_RegisterAxisInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_RightTrigger_RegisterAxisInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_ButtonMenu_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_ButtonMenu_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_ButtonMenu_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_ButtonHome_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_ButtonHome_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_ButtonHome_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_ButtonA_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_ButtonA_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_ButtonA_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_ButtonB_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_ButtonB_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_ButtonB_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_ButtonX_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_ButtonX_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_ButtonX_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_ButtonY_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_ButtonY_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_ButtonY_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_ButtonC_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_ButtonC_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_ButtonC_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_Dpad_LeftButton_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_Dpad_LeftButton_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_Dpad_LeftButton_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_Dpad_RightButton_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_Dpad_RightButton_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_Dpad_RightButton_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_Dpad_UpButton_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_Dpad_UpButton_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_Dpad_UpButton_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_Dpad_DownButton_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_Dpad_DownButton_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_Dpad_DownButton_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_Dpad_RegisterAxisInputMonitor() {
        assertNotNull(try { OH_GamePad_Dpad_RegisterAxisInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_Dpad_RegisterAxisInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_LeftThumbstick_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_LeftThumbstick_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_LeftThumbstick_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_LeftThumbstick_RegisterAxisInputMonitor() {
        assertNotNull(try { OH_GamePad_LeftThumbstick_RegisterAxisInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_LeftThumbstick_RegisterAxisInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_RightThumbstick_RegisterButtonInputMonitor() {
        assertNotNull(try { OH_GamePad_RightThumbstick_RegisterButtonInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_RightThumbstick_RegisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_RightThumbstick_RegisterAxisInputMonitor() {
        assertNotNull(try { OH_GamePad_RightThumbstick_RegisterAxisInputMonitor(null) } catch (e: Throwable) { logLine("OH_GamePad_RightThumbstick_RegisterAxisInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR })
    }

    @Test
    fun testOH_GamePad_LeftTrigger_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_LeftTrigger_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_LeftTrigger_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_LeftTrigger_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_LeftTrigger_UnregisterAxisInputMonitor() {
        val ret = try { OH_GamePad_LeftTrigger_UnregisterAxisInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_LeftTrigger_UnregisterAxisInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_LeftTrigger_UnregisterAxisInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_RightTrigger_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_RightTrigger_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_RightTrigger_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_RightTrigger_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_RightTrigger_UnregisterAxisInputMonitor() {
        val ret = try { OH_GamePad_RightTrigger_UnregisterAxisInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_RightTrigger_UnregisterAxisInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_RightTrigger_UnregisterAxisInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_ButtonMenu_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_ButtonMenu_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_ButtonMenu_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_ButtonMenu_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_ButtonHome_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_ButtonHome_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_ButtonHome_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_ButtonHome_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_ButtonA_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_ButtonA_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_ButtonA_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_ButtonA_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_ButtonB_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_ButtonB_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_ButtonB_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_ButtonB_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_ButtonX_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_ButtonX_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_ButtonX_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_ButtonX_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_ButtonY_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_ButtonY_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_ButtonY_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_ButtonY_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_ButtonC_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_ButtonC_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_ButtonC_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_ButtonC_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_Dpad_LeftButton_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_Dpad_LeftButton_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_Dpad_LeftButton_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_Dpad_LeftButton_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_Dpad_RightButton_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_Dpad_RightButton_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_Dpad_RightButton_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_Dpad_RightButton_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_Dpad_UpButton_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_Dpad_UpButton_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_Dpad_UpButton_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_Dpad_UpButton_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_Dpad_DownButton_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_Dpad_DownButton_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_Dpad_DownButton_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_Dpad_DownButton_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_Dpad_UnregisterAxisInputMonitor() {
        val ret = try { OH_GamePad_Dpad_UnregisterAxisInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_Dpad_UnregisterAxisInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_Dpad_UnregisterAxisInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_LeftThumbstick_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_LeftThumbstick_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_LeftThumbstick_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_LeftThumbstick_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_LeftThumbstick_UnregisterAxisInputMonitor() {
        val ret = try { OH_GamePad_LeftThumbstick_UnregisterAxisInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_LeftThumbstick_UnregisterAxisInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_LeftThumbstick_UnregisterAxisInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_RightThumbstick_UnregisterButtonInputMonitor() {
        val ret = try { OH_GamePad_RightThumbstick_UnregisterButtonInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_RightThumbstick_UnregisterButtonInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_RightThumbstick_UnregisterButtonInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_RightThumbstick_UnregisterAxisInputMonitor() {
        val ret = try { OH_GamePad_RightThumbstick_UnregisterAxisInputMonitor() } catch (e: Throwable) { logLine("OH_GamePad_RightThumbstick_UnregisterAxisInputMonitor (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
        assertNotNull(ret)
        logLine("OH_GamePad_RightThumbstick_UnregisterAxisInputMonitor ret=$ret")
    }

    @Test
    fun testOH_GamePad_ButtonEvent_GetDeviceId() {
        memScoped {
            val deviceId = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_GamePad_ButtonEvent_GetDeviceId(null, deviceId.ptr) } catch (e: Throwable) { logLine("OH_GamePad_ButtonEvent_GetDeviceId (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_ButtonEvent_GetDeviceId ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_ButtonEvent_GetButtonAction() {
        memScoped {
            val actionType = alloc<UIntVar>()
            val ret = try { OH_GamePad_ButtonEvent_GetButtonAction(null, actionType.ptr) } catch (e: Throwable) { logLine("OH_GamePad_ButtonEvent_GetButtonAction (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_ButtonEvent_GetButtonAction ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_ButtonEvent_GetButtonCode() {
        memScoped {
            val code = alloc<IntVar>()
            val ret = try { OH_GamePad_ButtonEvent_GetButtonCode(null, code.ptr) } catch (e: Throwable) { logLine("OH_GamePad_ButtonEvent_GetButtonCode (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_ButtonEvent_GetButtonCode ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_ButtonEvent_GetButtonCodeName() {
        memScoped {
            val codeName = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_GamePad_ButtonEvent_GetButtonCodeName(null, codeName.ptr) } catch (e: Throwable) { logLine("OH_GamePad_ButtonEvent_GetButtonCodeName (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_ButtonEvent_GetButtonCodeName ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_PressedButtons_GetCount() {
        memScoped {
            val count = alloc<IntVar>()
            val ret = try { OH_GamePad_PressedButtons_GetCount(null, count.ptr) } catch (e: Throwable) { logLine("OH_GamePad_PressedButtons_GetCount (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_PressedButtons_GetCount ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_PressedButtons_GetButtonInfo() {
        memScoped {
            val pressedButton = alloc<CPointerVar<GamePad_PressedButton>>()
            val ret = try { OH_GamePad_PressedButtons_GetButtonInfo(null, 0, pressedButton.ptr) } catch (e: Throwable) { logLine("OH_GamePad_PressedButtons_GetButtonInfo (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_PressedButtons_GetButtonInfo ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_DestroyPressedButton() {
        memScoped {
            val pressedButton = alloc<CPointerVar<GamePad_PressedButton>>()
            pressedButton.value = null
            val ret = try { OH_GamePad_DestroyPressedButton(pressedButton.ptr) } catch (e: Throwable) { logLine("OH_GamePad_DestroyPressedButton (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_DestroyPressedButton ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_PressedButton_GetButtonCode() {
        memScoped {
            val code = alloc<IntVar>()
            val ret = try { OH_GamePad_PressedButton_GetButtonCode(null, code.ptr) } catch (e: Throwable) { logLine("OH_GamePad_PressedButton_GetButtonCode (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_PressedButton_GetButtonCode ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_PressedButton_GetButtonCodeName() {
        memScoped {
            val codeName = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_GamePad_PressedButton_GetButtonCodeName(null, codeName.ptr) } catch (e: Throwable) { logLine("OH_GamePad_PressedButton_GetButtonCodeName (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_PressedButton_GetButtonCodeName ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_ButtonEvent_GetActionTime() {
        memScoped {
            val actionTime = alloc<LongVar>()
            val ret = try { OH_GamePad_ButtonEvent_GetActionTime(null, actionTime.ptr) } catch (e: Throwable) { logLine("OH_GamePad_ButtonEvent_GetActionTime (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_ButtonEvent_GetActionTime ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_AxisEvent_GetDeviceId() {
        memScoped {
            val deviceId = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_GamePad_AxisEvent_GetDeviceId(null, deviceId.ptr) } catch (e: Throwable) { logLine("OH_GamePad_AxisEvent_GetDeviceId (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_AxisEvent_GetDeviceId ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_AxisEvent_GetAxisSourceType() {
        memScoped {
            val axisSourceType = alloc<UIntVar>()
            val ret = try { OH_GamePad_AxisEvent_GetAxisSourceType(null, axisSourceType.ptr) } catch (e: Throwable) { logLine("OH_GamePad_AxisEvent_GetAxisSourceType (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_AxisEvent_GetAxisSourceType ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_AxisEvent_GetXAxisValue() {
        memScoped {
            val axisValue = alloc<DoubleVar>()
            val ret = try { OH_GamePad_AxisEvent_GetXAxisValue(null, axisValue.ptr) } catch (e: Throwable) { logLine("OH_GamePad_AxisEvent_GetXAxisValue (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_AxisEvent_GetXAxisValue ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_AxisEvent_GetYAxisValue() {
        memScoped {
            val axisValue = alloc<DoubleVar>()
            val ret = try { OH_GamePad_AxisEvent_GetYAxisValue(null, axisValue.ptr) } catch (e: Throwable) { logLine("OH_GamePad_AxisEvent_GetYAxisValue (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_AxisEvent_GetYAxisValue ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_AxisEvent_GetZAxisValue() {
        memScoped {
            val axisValue = alloc<DoubleVar>()
            val ret = try { OH_GamePad_AxisEvent_GetZAxisValue(null, axisValue.ptr) } catch (e: Throwable) { logLine("OH_GamePad_AxisEvent_GetZAxisValue (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_AxisEvent_GetZAxisValue ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_AxisEvent_GetRZAxisValue() {
        memScoped {
            val axisValue = alloc<DoubleVar>()
            val ret = try { OH_GamePad_AxisEvent_GetRZAxisValue(null, axisValue.ptr) } catch (e: Throwable) { logLine("OH_GamePad_AxisEvent_GetRZAxisValue (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_AxisEvent_GetRZAxisValue ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_AxisEvent_GetHatXAxisValue() {
        memScoped {
            val axisValue = alloc<DoubleVar>()
            val ret = try { OH_GamePad_AxisEvent_GetHatXAxisValue(null, axisValue.ptr) } catch (e: Throwable) { logLine("OH_GamePad_AxisEvent_GetHatXAxisValue (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_AxisEvent_GetHatXAxisValue ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_AxisEvent_GetHatYAxisValue() {
        memScoped {
            val axisValue = alloc<DoubleVar>()
            val ret = try { OH_GamePad_AxisEvent_GetHatYAxisValue(null, axisValue.ptr) } catch (e: Throwable) { logLine("OH_GamePad_AxisEvent_GetHatYAxisValue (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_AxisEvent_GetHatYAxisValue ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_AxisEvent_GetBrakeAxisValue() {
        memScoped {
            val axisValue = alloc<DoubleVar>()
            val ret = try { OH_GamePad_AxisEvent_GetBrakeAxisValue(null, axisValue.ptr) } catch (e: Throwable) { logLine("OH_GamePad_AxisEvent_GetBrakeAxisValue (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_AxisEvent_GetBrakeAxisValue ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_AxisEvent_GetGasAxisValue() {
        memScoped {
            val axisValue = alloc<DoubleVar>()
            val ret = try { OH_GamePad_AxisEvent_GetGasAxisValue(null, axisValue.ptr) } catch (e: Throwable) { logLine("OH_GamePad_AxisEvent_GetGasAxisValue (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_AxisEvent_GetGasAxisValue ret=$ret")
        }
    }

    @Test
    fun testOH_GamePad_AxisEvent_GetActionTime() {
        memScoped {
            val actionTime = alloc<LongVar>()
            val ret = try { OH_GamePad_AxisEvent_GetActionTime(null, actionTime.ptr) } catch (e: Throwable) { logLine("OH_GamePad_AxisEvent_GetActionTime (API 21) exception: $e"); GAME_CONTROLLER_PARAM_ERROR }
            assertNotNull(ret)
            logLine("OH_GamePad_AxisEvent_GetActionTime ret=$ret")
        }
    }
}
