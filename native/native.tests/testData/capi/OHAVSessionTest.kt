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
import platform.AVSessionKit.OHAVSession.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OHAVSessionTest {

    private fun logLine(msg: String) = println("[stdout] OHAVSessionTest $msg")

    // ==================== 枚举（native_avsession.h / native_avsession_errors.h）====================
    @Test
    fun testEnum_AVSession_ErrCode() {
        assertEquals(AV_SESSION_ERR_SUCCESS.toInt(), 0)
        assertEquals(AV_SESSION_ERR_INVALID_PARAMETER.toInt(), 401)
        assertEquals(AV_SESSION_ERR_SERVICE_EXCEPTION.toInt(), 6600101)
        assertEquals(AV_SESSION_ERR_CODE_SESSION_NOT_EXIST.toInt(), 6600102)
        assertEquals(AV_SESSION_ERR_CODE_COMMAND_INVALID.toInt(), 6600105)
        assertEquals(AV_SESSION_ERR_CODE_SESSION_INACTIVE.toInt(), 6600106)
        assertEquals(AV_SESSION_ERR_CODE_MESSAGE_OVERLOAD.toInt(), 6600107)
        logLine("AVSession_ErrCode passed")
    }

    @Test
    fun testEnum_AVSession_Type() {
        assertEquals(SESSION_TYPE_AUDIO.toInt(), 0)
        assertEquals(SESSION_TYPE_VIDEO.toInt(), 1)
        assertEquals(SESSION_TYPE_VOICE_CALL.toInt(), 2)
        assertEquals(SESSION_TYPE_VIDEO_CALL.toInt(), 3)
        logLine("AVSession_Type passed")
    }

    @Test
    fun testEnum_AVSession_PlaybackState() {
        assertEquals(PLAYBACK_STATE_INITIAL.toInt(), 0)
        assertEquals(PLAYBACK_STATE_PREPARING.toInt(), 1)
        assertEquals(PLAYBACK_STATE_PLAYING.toInt(), 2)
        assertEquals(PLAYBACK_STATE_PAUSED.toInt(), 3)
        assertEquals(PLAYBACK_STATE_FAST_FORWARDING.toInt(), 4)
        assertEquals(PLAYBACK_STATE_REWINDED.toInt(), 5)
        assertEquals(PLAYBACK_STATE_STOPPED.toInt(), 6)
        assertEquals(PLAYBACK_STATE_COMPLETED.toInt(), 7)
        assertEquals(PLAYBACK_STATE_RELEASED.toInt(), 8)
        assertEquals(PLAYBACK_STATE_ERROR.toInt(), 9)
        assertEquals(PLAYBACK_STATE_IDLE.toInt(), 10)
        assertEquals(PLAYBACK_STATE_BUFFERING.toInt(), 11)
        assertEquals(PLAYBACK_STATE_MAX.toInt(), 12)
        logLine("AVSession_PlaybackState passed")
    }

    @Test
    fun testEnum_AVSession_LoopMode() {
        assertEquals(LOOP_MODE_SEQUENCE.toInt(), 0)
        assertEquals(LOOP_MODE_SINGLE.toInt(), 1)
        assertEquals(LOOP_MODE_LIST.toInt(), 2)
        assertEquals(LOOP_MODE_SHUFFLE.toInt(), 3)
        assertEquals(LOOP_MODE_CUSTOM.toInt(), 4)
        logLine("AVSession_LoopMode passed")
    }

    @Test
    fun testEnum_AVSession_ControlCommand() {
        assertEquals(CONTROL_CMD_INVALID.toInt(), -1)
        assertEquals(CONTROL_CMD_PLAY.toInt(), 0)
        assertEquals(CONTROL_CMD_PAUSE.toInt(), 1)
        assertEquals(CONTROL_CMD_STOP.toInt(), 2)
        assertEquals(CONTROL_CMD_PLAY_NEXT.toInt(), 3)
        assertEquals(CONTROL_CMD_PLAY_PREVIOUS.toInt(), 4)
        logLine("AVSession_ControlCommand passed")
    }

    @Test
    fun testEnum_AVSessionCallback_Result() {
        assertEquals(AVSESSION_CALLBACK_RESULT_SUCCESS.toInt(), 0)
        assertEquals(AVSESSION_CALLBACK_RESULT_FAILURE.toInt(), -1)
        logLine("AVSessionCallback_Result passed")
    }

    @Test
    fun testEnum_AVMetadata_Result() {
        assertEquals(AVMETADATA_SUCCESS.toInt(), 0)
        assertEquals(AVMETADATA_ERROR_INVALID_PARAM.toInt(), 1)
        assertEquals(AVMETADATA_ERROR_NO_MEMORY.toInt(), 2)
        logLine("AVMetadata_Result passed")
    }

    @Test
    fun testEnum_AVMetadata_SkipIntervals() {
        assertEquals(SECONDS_10.toInt(), 10)
        assertEquals(SECONDS_15.toInt(), 15)
        assertEquals(SECONDS_30.toInt(), 30)
        logLine("AVMetadata_SkipIntervals passed")
    }

    @Test
    fun testEnum_AVMetadata_DisplayTag() {
        assertEquals(AVSESSION_DISPLAYTAG_AUDIO_VIVID.toInt(), 1)
        logLine("AVMetadata_DisplayTag passed")
    }

    // ==================== OH_AVMetadataBuilder ====================

    @Test
    fun testOH_AVMetadataBuilder_Create() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            val ret = OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(ret)
            logLine("OH_AVMetadataBuilder_Create ret=$ret")
            if (builderPtr.value != null) OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_Destroy() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            val ret = OH_AVMetadataBuilder_Destroy(builderPtr.value)
            assertNotNull(ret)
            logLine("OH_AVMetadataBuilder_Destroy ret=$ret")
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetAssetId() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetAssetId(builderPtr.value, "id")
            logLine("OH_AVMetadataBuilder_SetAssetId=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetTitle() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetTitle(builderPtr.value, "title")
            logLine("OH_AVMetadataBuilder_SetTitle=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetArtist() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetArtist(builderPtr.value, "artist")
            logLine("OH_AVMetadataBuilder_SetArtist=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetAuthor() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetAuthor(builderPtr.value, "author")
            logLine("OH_AVMetadataBuilder_SetAuthor=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetAlbum() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetAlbum(builderPtr.value, "album")
            logLine("OH_AVMetadataBuilder_SetAlbum=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetWriter() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetWriter(builderPtr.value, "writer")
            logLine("OH_AVMetadataBuilder_SetWriter=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetComposer() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetComposer(builderPtr.value, "composer")
            logLine("OH_AVMetadataBuilder_SetComposer=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetDuration() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetDuration(builderPtr.value, 1000L)
            logLine("OH_AVMetadataBuilder_SetDuration=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetMediaImageUri() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetMediaImageUri(builderPtr.value, "uri")
            logLine("OH_AVMetadataBuilder_SetMediaImageUri=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetSubtitle() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetSubtitle(builderPtr.value, "sub")
            logLine("OH_AVMetadataBuilder_SetSubtitle=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetDescription() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetDescription(builderPtr.value, "desc")
            logLine("OH_AVMetadataBuilder_SetDescription=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetLyric() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetLyric(builderPtr.value, "lyric")
            logLine("OH_AVMetadataBuilder_SetLyric=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetSkipIntervals() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetSkipIntervals(builderPtr.value, SECONDS_10)
            logLine("OH_AVMetadataBuilder_SetSkipIntervals=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_SetDisplayTags() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            OH_AVMetadataBuilder_SetDisplayTags(builderPtr.value, 0)
            logLine("OH_AVMetadataBuilder_SetDisplayTags=called")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadataBuilder_GenerateAVMetadata() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            val metaPtr = alloc<CPointerVar<OH_AVMetadataStruct>>()
            val ret = OH_AVMetadataBuilder_GenerateAVMetadata(builderPtr.value, metaPtr.ptr)
            assertNotNull(ret)
            logLine("OH_AVMetadataBuilder_GenerateAVMetadata ret=$ret")
            if (metaPtr.value != null) OH_AVMetadata_Destroy(metaPtr.value)
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    @Test
    fun testOH_AVMetadata_Destroy() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            assertNotNull(builderPtr.value)
            val metaPtr = alloc<CPointerVar<OH_AVMetadataStruct>>()
            OH_AVMetadataBuilder_GenerateAVMetadata(builderPtr.value, metaPtr.ptr)
            assertNotNull(metaPtr.value)
            val ret = OH_AVMetadata_Destroy(metaPtr.value)
            assertNotNull(ret)
            logLine("OH_AVMetadata_Destroy ret=$ret")
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }

    // ==================== OH_AVSession ====================

    @Test
    fun testOH_AVSession_Create() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            val ret = OH_AVSession_Create(SESSION_TYPE_AUDIO, "tag", "bundle", "ability", sessionPtr.ptr)
            assertNotNull(ret)
            logLine("OH_AVSession_Create ret=$ret")
            if (sessionPtr.value != null) OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_Destroy() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            val ret = OH_AVSession_Destroy(sessionPtr.value)
            logLine("OH_AVSession_Destroy ret=$ret")
        }
    }

    @Test
    fun testOH_AVSession_Activate() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            val ret = OH_AVSession_Activate(sessionPtr.value)
            logLine("OH_AVSession_Activate ret=$ret")
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_Deactivate() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            OH_AVSession_Activate(sessionPtr.value)
            val ret = OH_AVSession_Deactivate(sessionPtr.value)
            logLine("OH_AVSession_Deactivate ret=$ret")
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_GetSessionType() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            val ret = OH_AVSession_GetSessionType(sessionPtr.value, null)
            logLine("OH_AVSession_GetSessionType(null) ret=$ret")
            val typeVar = alloc<UIntVar>()
            OH_AVSession_GetSessionType(sessionPtr.value, typeVar.ptr)
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_GetSessionId() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            val idPtr = alloc<CPointerVar<ByteVar>>()
            val ret = OH_AVSession_GetSessionId(sessionPtr.value, idPtr.ptr)
            logLine("OH_AVSession_GetSessionId ret=$ret")
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_SetPlaybackState() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            val ret = OH_AVSession_SetPlaybackState(sessionPtr.value, PLAYBACK_STATE_IDLE)
            logLine("OH_AVSession_SetPlaybackState ret=$ret")
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_SetPlaybackPosition() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            val pos = alloc<AVSession_PlaybackPosition>().apply { elapsedTime = 0L; updateTime = 0L }
            val ret = OH_AVSession_SetPlaybackPosition(sessionPtr.value, pos.ptr)
            logLine("OH_AVSession_SetPlaybackPosition ret=$ret")
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_SetFavorite() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            val ret = OH_AVSession_SetFavorite(sessionPtr.value, false)
            logLine("OH_AVSession_SetFavorite ret=$ret")
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_SetLoopMode() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            val ret = OH_AVSession_SetLoopMode(sessionPtr.value, LOOP_MODE_SEQUENCE)
            logLine("OH_AVSession_SetLoopMode ret=$ret")
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_RegisterCommandCallback() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            val ret = OH_AVSession_RegisterCommandCallback(sessionPtr.value, CONTROL_CMD_PLAY, null, null)
            logLine("OH_AVSession_RegisterCommandCallback ret=$ret")
            OH_AVSession_UnregisterCommandCallback(sessionPtr.value, CONTROL_CMD_PLAY, null)
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_UnregisterCommandCallback() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            OH_AVSession_RegisterCommandCallback(sessionPtr.value, CONTROL_CMD_PLAY, null, null)
            val ret = OH_AVSession_UnregisterCommandCallback(sessionPtr.value, CONTROL_CMD_PLAY, null)
            logLine("OH_AVSession_UnregisterCommandCallback ret=$ret")
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_RegisterForwardCallback() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            val ret = OH_AVSession_RegisterForwardCallback(sessionPtr.value, null, null)
            logLine("OH_AVSession_RegisterForwardCallback ret=$ret")
            OH_AVSession_UnregisterForwardCallback(sessionPtr.value, null)
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_UnregisterForwardCallback() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            OH_AVSession_RegisterForwardCallback(sessionPtr.value, null, null)
            val ret = OH_AVSession_UnregisterForwardCallback(sessionPtr.value, null)
            logLine("OH_AVSession_UnregisterForwardCallback ret=$ret")
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_RegisterRewindCallback() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            val ret = OH_AVSession_RegisterRewindCallback(sessionPtr.value, null, null)
            logLine("OH_AVSession_RegisterRewindCallback ret=$ret")
            OH_AVSession_UnregisterRewindCallback(sessionPtr.value, null)
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_UnregisterRewindCallback() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            OH_AVSession_RegisterRewindCallback(sessionPtr.value, null, null)
            val ret = OH_AVSession_UnregisterRewindCallback(sessionPtr.value, null)
            logLine("OH_AVSession_UnregisterRewindCallback ret=$ret")
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_RegisterSeekCallback() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            val ret = OH_AVSession_RegisterSeekCallback(sessionPtr.value, null, null)
            logLine("OH_AVSession_RegisterSeekCallback ret=$ret")
            OH_AVSession_UnregisterSeekCallback(sessionPtr.value, null)
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_UnregisterSeekCallback() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            OH_AVSession_RegisterSeekCallback(sessionPtr.value, null, null)
            val ret = OH_AVSession_UnregisterSeekCallback(sessionPtr.value, null)
            logLine("OH_AVSession_UnregisterSeekCallback ret=$ret")
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_RegisterSetLoopModeCallback() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            val ret = OH_AVSession_RegisterSetLoopModeCallback(sessionPtr.value, null, null)
            logLine("OH_AVSession_RegisterSetLoopModeCallback ret=$ret")
            OH_AVSession_UnregisterSetLoopModeCallback(sessionPtr.value, null)
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_UnregisterSetLoopModeCallback() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            OH_AVSession_RegisterSetLoopModeCallback(sessionPtr.value, null, null)
            val ret = OH_AVSession_UnregisterSetLoopModeCallback(sessionPtr.value, null)
            logLine("OH_AVSession_UnregisterSetLoopModeCallback ret=$ret")
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_RegisterToggleFavoriteCallback() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            val ret = OH_AVSession_RegisterToggleFavoriteCallback(sessionPtr.value, null, null)
            logLine("OH_AVSession_RegisterToggleFavoriteCallback ret=$ret")
            OH_AVSession_UnregisterToggleFavoriteCallback(sessionPtr.value, null)
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_UnregisterToggleFavoriteCallback() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            
            OH_AVSession_Activate(sessionPtr.value)
            OH_AVSession_RegisterToggleFavoriteCallback(sessionPtr.value, null, null)
            val ret = OH_AVSession_UnregisterToggleFavoriteCallback(sessionPtr.value, null)
            logLine("OH_AVSession_UnregisterToggleFavoriteCallback ret=$ret")
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
        }
    }

    @Test
    fun testOH_AVSession_SetAVMetadata() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<OH_AVSession>>()
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            OH_AVMetadataBuilder_Create(builderPtr.ptr)
            val metaPtr = alloc<CPointerVar<OH_AVMetadataStruct>>()
            OH_AVMetadataBuilder_GenerateAVMetadata(builderPtr.value, metaPtr.ptr)
            OH_AVSession_Create(SESSION_TYPE_AUDIO, "t", "b", "a", sessionPtr.ptr)
            OH_AVSession_Activate(sessionPtr.value)
            val ret = OH_AVSession_SetAVMetadata(sessionPtr.value, metaPtr.value)
            logLine("OH_AVSession_SetAVMetadata ret=$ret")
            OH_AVSession_Deactivate(sessionPtr.value)
            OH_AVSession_Destroy(sessionPtr.value)
            OH_AVMetadata_Destroy(metaPtr.value)
            OH_AVMetadataBuilder_Destroy(builderPtr.value)
        }
    }
}
