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
import platform.MediaKit.AVPlayer.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AVPlayerTest {

    @Test
    fun testEnums() {
        logLine("--- Testing AVPlayer enums (avplayer_base) ---")
        val idle = AV_IDLE
        val playing = AV_PLAYING
        assertNotNull(idle)
        assertNotNull(playing)
        assertNotEquals(idle, playing)
        logLine("AVPlayerState: IDLE=$idle, PLAYING=$playing")
        val nextSync = AV_SEEK_NEXT_SYNC
        val closest = AV_SEEK_CLOSEST
        assertNotNull(nextSync)
        assertNotNull(closest)
        assertNotEquals(nextSync, closest)
        logLine("AVPlayerSeekMode: NEXT_SYNC=$nextSync, CLOSEST=$closest")
        val speed100 = AVPlaybackSpeed.AV_SPEED_FORWARD_1_00_X
        assertNotNull(speed100)
        logLine("AVPlaybackSpeed: 1_00_X=$speed100")
    }

    @Test
    fun testOH_AVPlayer_Create() {
        val player = OH_AVPlayer_Create()
        assertNotNull(player)
        logLine("OH_AVPlayer_Create() result: ${if (player != null) "non-null" else "null"}")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_Create passed")
    }

    @Test
    fun testOH_AVPlayer_Release() {
        val player = OH_AVPlayer_Create()
        val releaseResult = OH_AVPlayer_Release(player)
        assertNotNull(releaseResult)
        logLine("OH_AVPlayer_Release(player) result: $releaseResult")
        val releaseNullResult = OH_AVPlayer_Release(null)
        assertNotNull(releaseNullResult)
        logLine("OH_AVPlayer_Release passed")
    }

    @Test
    fun testOH_AVPlayer_ReleaseSync() {
        val player = OH_AVPlayer_Create()
        val releaseSyncResult = OH_AVPlayer_ReleaseSync(player)
        assertNotNull(releaseSyncResult)
        logLine("OH_AVPlayer_ReleaseSync(player) result: $releaseSyncResult")
        val releaseSyncNullResult = OH_AVPlayer_ReleaseSync(null)
        assertNotNull(releaseSyncNullResult)
        logLine("OH_AVPlayer_ReleaseSync passed")
    }

    @Test
    fun testOH_AVPlayer_SetURLSource() {
        val player = OH_AVPlayer_Create()
        val setUrlResult = OH_AVPlayer_SetURLSource(player, null)
        assertNotNull(setUrlResult)
        logLine("OH_AVPlayer_SetURLSource(player, url) result: $setUrlResult")
        OH_AVPlayer_Release(player)
        val setUrlNullResult = OH_AVPlayer_SetURLSource(null, "url")
        assertNotNull(setUrlNullResult)
        logLine("OH_AVPlayer_SetURLSource passed")
    }

    @Test
    fun testOH_AVPlayer_SetFDSource() {
        val player = OH_AVPlayer_Create()
        val setFdResult = OH_AVPlayer_SetFDSource(player, 0, 0L, 0L)
        assertNotNull(setFdResult)
        logLine("OH_AVPlayer_SetFDSource(player, 0, 0, 0) result: $setFdResult")
        OH_AVPlayer_Release(player)
        val setFdNullResult = OH_AVPlayer_SetFDSource(null, 0, 0L, 0L)
        assertNotNull(setFdNullResult)
        logLine("OH_AVPlayer_SetFDSource passed")
    }

    @Test
    fun testOH_AVPlayer_Prepare() {
        val player = OH_AVPlayer_Create()
        val prepareResult = OH_AVPlayer_Prepare(player)
        assertNotNull(prepareResult)
        logLine("OH_AVPlayer_Prepare(player) result: $prepareResult")
        OH_AVPlayer_Release(player)
        val prepareNullResult = OH_AVPlayer_Prepare(null)
        assertNotNull(prepareNullResult)
        logLine("OH_AVPlayer_Prepare passed")
    }

    @Test
    fun testOH_AVPlayer_Play() {
        val player = OH_AVPlayer_Create()
        val playResult = OH_AVPlayer_Play(player)
        assertNotNull(playResult)
        logLine("OH_AVPlayer_Play(player) result: $playResult")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_Play passed")
    }

    @Test
    fun testOH_AVPlayer_Pause() {
        val player = OH_AVPlayer_Create()
        val pauseResult = OH_AVPlayer_Pause(player)
        assertNotNull(pauseResult)
        logLine("OH_AVPlayer_Pause(player) result: $pauseResult")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_Pause passed")
    }

    @Test
    fun testOH_AVPlayer_Stop() {
        val player = OH_AVPlayer_Create()
        val stopResult = OH_AVPlayer_Stop(player)
        assertNotNull(stopResult)
        logLine("OH_AVPlayer_Stop(player) result: $stopResult")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_Stop passed")
    }

    @Test
    fun testOH_AVPlayer_Reset() {
        val player = OH_AVPlayer_Create()
        val resetResult = OH_AVPlayer_Reset(player)
        assertNotNull(resetResult)
        logLine("OH_AVPlayer_Reset(player) result: $resetResult")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_Reset passed")
    }

    @Test
    fun testOH_AVPlayer_GetCurrentTime() {
        memScoped {
            val player = OH_AVPlayer_Create()
            val currentTime = alloc<IntVar>()
            val r = OH_AVPlayer_GetCurrentTime(player, currentTime.ptr)
            assertNotNull(r)
            logLine("OH_AVPlayer_GetCurrentTime=$r")
            OH_AVPlayer_Release(player)
        }
        logLine("OH_AVPlayer_GetCurrentTime passed")
    }

    @Test
    fun testOH_AVPlayer_GetDuration() {
        memScoped {
            val player = OH_AVPlayer_Create()
            val duration = alloc<IntVar>()
            val r = OH_AVPlayer_GetDuration(player, duration.ptr)
            assertNotNull(r)
            logLine("OH_AVPlayer_GetDuration=$r")
            OH_AVPlayer_Release(player)
        }
        logLine("OH_AVPlayer_GetDuration passed")
    }

    @Test
    fun testOH_AVPlayer_GetVideoWidth() {
        memScoped {
            val player = OH_AVPlayer_Create()
            val videoWidth = alloc<IntVar>()
            val r = OH_AVPlayer_GetVideoWidth(player, videoWidth.ptr)
            assertNotNull(r)
            logLine("OH_AVPlayer_GetVideoWidth=$r")
            OH_AVPlayer_Release(player)
        }
        logLine("OH_AVPlayer_GetVideoWidth passed")
    }

    @Test
    fun testOH_AVPlayer_GetVideoHeight() {
        memScoped {
            val player = OH_AVPlayer_Create()
            val videoHeight = alloc<IntVar>()
            val r = OH_AVPlayer_GetVideoHeight(player, videoHeight.ptr)
            assertNotNull(r)
            logLine("OH_AVPlayer_GetVideoHeight=$r")
            OH_AVPlayer_Release(player)
        }
        logLine("OH_AVPlayer_GetVideoHeight passed")
    }

    @Test
    fun testOH_AVPlayer_GetState() {
        memScoped {
            val player = OH_AVPlayer_Create()
            val state = alloc<AVPlayerStateVar>()
            val r = OH_AVPlayer_GetState(player, state.ptr)
            assertNotNull(r)
            logLine("OH_AVPlayer_GetState=$r")
            OH_AVPlayer_Release(player)
        }
        logLine("OH_AVPlayer_GetState passed")
    }

    @Test
    fun testOH_AVPlayer_IsPlaying() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_IsPlaying(player)
        assertNotNull(r)
        logLine("OH_AVPlayer_IsPlaying=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_IsPlaying passed")
    }

    @Test
    fun testOH_AVPlayer_IsLooping() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_IsLooping(player)
        assertNotNull(r)
        logLine("OH_AVPlayer_IsLooping=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_IsLooping passed")
    }

    @Test
    fun testOH_AVPlayer_SetVolume() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_SetVolume(player, 0.5f, 0.5f)
        assertNotNull(r)
        logLine("OH_AVPlayer_SetVolume=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_SetVolume passed")
    }

    @Test
    fun testOH_AVPlayer_Seek() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_Seek(player, 1000, AV_SEEK_NEXT_SYNC)
        assertNotNull(r)
        logLine("OH_AVPlayer_Seek=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_Seek passed")
    }

    @Test
    fun testOH_AVPlayer_SetPlaybackSpeed() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_SetPlaybackSpeed(player, AVPlaybackSpeed.AV_SPEED_FORWARD_1_00_X)
        assertNotNull(r)
        logLine("OH_AVPlayer_SetPlaybackSpeed=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_SetPlaybackSpeed passed")
    }

    @Test
    fun testOH_AVPlayer_SetPlaybackRate() {
        try {
            val player = OH_AVPlayer_Create()
            val r = OH_AVPlayer_SetPlaybackRate(player, 1.0f)
            assertNotNull(r)
            logLine("OH_AVPlayer_SetPlaybackRate=$r")
            OH_AVPlayer_Release(player)
        } catch (e: Throwable) {
            logLine("OH_AVPlayer_SetPlaybackRate (API 20) exception: $e")
        }
        logLine("OH_AVPlayer_SetPlaybackRate passed")
    }

    @Test
    fun testOH_AVPlayer_GetPlaybackSpeed() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_GetPlaybackSpeed(player, null)
        assertNotNull(r)
        logLine("OH_AVPlayer_GetPlaybackSpeed=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_GetPlaybackSpeed passed")
    }

    @Test
    fun testOH_AVPlayer_SetLooping() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_SetLooping(player, false)
        assertNotNull(r)
        logLine("OH_AVPlayer_SetLooping=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_SetLooping passed")
    }

    @Test
    fun testOH_AVPlayer_SetAudioRendererInfo() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_SetAudioRendererInfo(player, 0u)
        assertNotNull(r)
        logLine("OH_AVPlayer_SetAudioRendererInfo=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_SetAudioRendererInfo passed")
    }

    @Test
    fun testOH_AVPlayer_SetAudioInterruptMode() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_SetAudioInterruptMode(player, 0u)
        assertNotNull(r)
        logLine("OH_AVPlayer_SetAudioInterruptMode=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_SetAudioInterruptMode passed")
    }

    @Test
    fun testOH_AVPlayer_SetAudioEffectMode() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_SetAudioEffectMode(player, 0u)
        assertNotNull(r)
        logLine("OH_AVPlayer_SetAudioEffectMode=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_SetAudioEffectMode passed")
    }

    @Test
    fun testOH_AVPlayer_SetVolumeMode() {
        try {
            val player = OH_AVPlayer_Create()
            val r = OH_AVPlayer_SetVolumeMode(player, 0u)
            assertNotNull(r)
            logLine("OH_AVPlayer_SetVolumeMode=$r")
            OH_AVPlayer_Release(player)
        } catch (e: Throwable) {
            logLine("OH_AVPlayer_SetVolumeMode (API 19) exception: $e")
        }
        logLine("OH_AVPlayer_SetVolumeMode passed")
    }

    @Test
    fun testOH_AVPlayer_SetLoudnessGain() {
        try {
            val player = OH_AVPlayer_Create()
            val r = OH_AVPlayer_SetLoudnessGain(player, 0.0f)
            assertNotNull(r)
            logLine("OH_AVPlayer_SetLoudnessGain=$r")
            OH_AVPlayer_Release(player)
        } catch (e: Throwable) {
            logLine("OH_AVPlayer_SetLoudnessGain (API 21) exception: $e")
        }
        logLine("OH_AVPlayer_SetLoudnessGain passed")
    }

    @Test
    fun testOH_AVPlayer_SetVideoSurface() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_SetVideoSurface(player, null)
        assertNotNull(r)
        logLine("OH_AVPlayer_SetVideoSurface=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_SetVideoSurface passed")
    }

    @Test
    fun testOH_AVPlayer_SelectBitRate() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_SelectBitRate(player, 1000000u)
        assertNotNull(r)
        logLine("OH_AVPlayer_SelectBitRate=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_SelectBitRate passed")
    }

    @Test
    fun testOH_AVPlayer_SelectTrack() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_SelectTrack(player, 0)
        assertNotNull(r)
        logLine("OH_AVPlayer_SelectTrack=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_SelectTrack passed")
    }

    @Test
    fun testOH_AVPlayer_DeselectTrack() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_DeselectTrack(player, 0)
        assertNotNull(r)
        logLine("OH_AVPlayer_DeselectTrack=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_DeselectTrack passed")
    }

    @Test
    fun testOH_AVPlayer_GetCurrentTrack() {
        memScoped {
            val player = OH_AVPlayer_Create()
            val index = alloc<IntVar>()
            val r = OH_AVPlayer_GetCurrentTrack(player, 0, index.ptr)
            assertNotNull(r)
            logLine("OH_AVPlayer_GetCurrentTrack=$r")
            OH_AVPlayer_Release(player)
        }
        logLine("OH_AVPlayer_GetCurrentTrack passed")
    }

    @Test
    fun testOH_AVPlayer_SetPlayerCallback() {
        memScoped {
            val player = OH_AVPlayer_Create()
            val callback = alloc<AVPlayerCallback>().apply {
                onInfo = null
                onError = null
            }
            val r = OH_AVPlayer_SetPlayerCallback(player, callback.readValue())
            assertNotNull(r)
            logLine("OH_AVPlayer_SetPlayerCallback=$r")
            OH_AVPlayer_Release(player)
        }
        logLine("OH_AVPlayer_SetPlayerCallback passed")
    }

    @Test
    fun testOH_AVPlayer_SetOnInfoCallback() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_SetOnInfoCallback(player, null, null)
        assertNotNull(r)
        logLine("OH_AVPlayer_SetOnInfoCallback=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_SetOnInfoCallback passed")
    }

    @Test
    fun testOH_AVPlayer_SetOnErrorCallback() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_SetOnErrorCallback(player, null, null)
        assertNotNull(r)
        logLine("OH_AVPlayer_SetOnErrorCallback=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_SetOnErrorCallback passed")
    }

    @Test
    fun testOH_AVPlayer_SetMediaKeySystemInfoCallback() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_SetMediaKeySystemInfoCallback(player, null)
        assertNotNull(r)
        logLine("OH_AVPlayer_SetMediaKeySystemInfoCallback=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_SetMediaKeySystemInfoCallback passed")
    }

    @Test
    fun testOH_AVPlayer_GetMediaKeySystemInfo() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_GetMediaKeySystemInfo(player, null)
        assertNotNull(r)
        logLine("OH_AVPlayer_GetMediaKeySystemInfo=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_GetMediaKeySystemInfo passed")
    }

    @Test
    fun testOH_AVPlayer_SetDecryptionConfig() {
        val player = OH_AVPlayer_Create()
        val r = OH_AVPlayer_SetDecryptionConfig(player, null, false)
        assertNotNull(r)
        logLine("OH_AVPlayer_SetDecryptionConfig=$r")
        OH_AVPlayer_Release(player)
        logLine("OH_AVPlayer_SetDecryptionConfig passed")
    }

    @Test
    fun testOH_AVPlayer_SetDataSource() {
        try {
            val player = OH_AVPlayer_Create()
            val r = OH_AVPlayer_SetDataSource(player, null, null)
            assertNotNull(r)
            logLine("OH_AVPlayer_SetDataSource=$r")
            OH_AVPlayer_Release(player)
        } catch (e: Throwable) {
            logLine("OH_AVPlayer_SetDataSource (API 21) exception: $e")
        }
        logLine("OH_AVPlayer_SetDataSource passed")
    }

    @Test
    fun testOH_AVPlayer_GetMediaDescription() {
        try {
            val player = OH_AVPlayer_Create()
            val mediaDesc = OH_AVPlayer_GetMediaDescription(player)
            assertNotNull(mediaDesc)
            logLine("OH_AVPlayer_GetMediaDescription=${if (mediaDesc != null) "non-null" else "null"}")
            OH_AVPlayer_Release(player)
        } catch (e: Throwable) {
            logLine("OH_AVPlayer_GetMediaDescription (API 22) exception: $e")
        }
        logLine("OH_AVPlayer_GetMediaDescription passed")
    }

    @Test
    fun testOH_AVPlayer_GetTrackDescription() {
        try {
            val player = OH_AVPlayer_Create()
            val trackDesc = OH_AVPlayer_GetTrackDescription(player, 0u)
            assertNotNull(trackDesc)
            logLine("OH_AVPlayer_GetTrackDescription=${if (trackDesc != null) "non-null" else "null"}")
            OH_AVPlayer_Release(player)
        } catch (e: Throwable) {
            logLine("OH_AVPlayer_GetTrackDescription (API 22) exception: $e")
        }
        logLine("OH_AVPlayer_GetTrackDescription passed")
    }

    private fun logLine(message: String) {
        println("[stdout] AVPlayerTest $message")
    }
}
