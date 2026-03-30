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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.drm.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class Kba_drmTest {

    private fun logLine(msg: String) = println(msg)

    // drm/drm.h — kernel UAPI，仅常量/宏
    @Test
    fun testDrm_h() {
        assertNotNull(DRM_NAME)
        assertEquals<Int>(5, DRM_MIN_ORDER)
        logLine("DRM_NAME=$DRM_NAME DRM_MIN_ORDER=$DRM_MIN_ORDER")
    }

    // drm/amdgpu_drm.h
    @Test
    fun testAmdgpu_drm_h() {
        assertEquals<Int>(0, DRM_AMDGPU_GEM_CREATE)
        logLine("DRM_AMDGPU_GEM_CREATE=$DRM_AMDGPU_GEM_CREATE")
    }

    // drm/armada_drm.h
    @Test
    fun testArmada_drm_h() {
        assertEquals<Int>(0, DRM_ARMADA_GEM_CREATE)
        logLine("DRM_ARMADA_GEM_CREATE=$DRM_ARMADA_GEM_CREATE")
    }

    // drm/drm_fourcc.h
    @Test
    fun testDrm_fourcc_h() {
        assertEquals<Int>(0, DRM_FORMAT_INVALID)
        logLine("DRM_FORMAT_INVALID=$DRM_FORMAT_INVALID")
    }

    // drm/drm_mode.h
    @Test
    fun testDrm_mode_h() {
        assertEquals<Int>(32, DRM_CONNECTOR_NAME_LEN)
        logLine("DRM_CONNECTOR_NAME_LEN=$DRM_CONNECTOR_NAME_LEN")
    }

    // drm/drm_sarea.h
    @Test
    fun testDrm_sarea_h() {
        assertNotNull(SAREA_MAX)
        logLine("SAREA_MAX=$SAREA_MAX")
    }

    // drm/etnaviv_drm.h
    @Test
    fun testEtnaviv_drm_h() {
        assertEquals<Int>(0x01, ETNAVIV_PARAM_GPU_MODEL)
        logLine("ETNAVIV_PARAM_GPU_MODEL=$ETNAVIV_PARAM_GPU_MODEL")
    }

    // drm/exynos_drm.h
    @Test
    fun testExynos_drm_h() {
        assertEquals<Int>(0, DRM_EXYNOS_GEM_CREATE)
        logLine("DRM_EXYNOS_GEM_CREATE=$DRM_EXYNOS_GEM_CREATE")
    }

    // drm/i810_drm.h
    @Test
    fun testI810_drm_h() {
        assertEquals<Int>(12, I810_DMA_BUF_ORDER)
        logLine("I810_DMA_BUF_ORDER=$I810_DMA_BUF_ORDER")
    }

    // drm/i915_drm.h
    @Test
    fun testI915_drm_h() {
        assertNotNull(I915_ERROR_UEVENT)
        logLine("I915_ERROR_UEVENT=$I915_ERROR_UEVENT")
    }

    // drm/lima_drm.h
    @Test
    fun testLima_drm_h() {
        assertEquals<Int>(1, LIMA_BO_FLAG_HEAP)
        logLine("LIMA_BO_FLAG_HEAP=$LIMA_BO_FLAG_HEAP")
    }

    // drm/mga_drm.h
    @Test
    fun testMga_drm_h() {
        assertEquals<Int>(0x1, MGA_F)
        logLine("MGA_F=$MGA_F")
    }

    // drm/msm_drm.h
    @Test
    fun testMsm_drm_h() {
        assertEquals<Int>(0x00, MSM_PIPE_NONE)
        logLine("MSM_PIPE_NONE=$MSM_PIPE_NONE")
    }

    // drm/nouveau_drm.h
    @Test
    fun testNouveau_drm_h() {
        assertEquals<Int>(1, NOUVEAU_GEM_DOMAIN_CPU)
        logLine("NOUVEAU_GEM_DOMAIN_CPU=$NOUVEAU_GEM_DOMAIN_CPU")
    }

    // drm/omap_drm.h
    @Test
    fun testOmap_drm_h() {
        assertEquals<Int>(1, OMAP_PARAM_CHIPSET_ID)
        logLine("OMAP_PARAM_CHIPSET_ID=$OMAP_PARAM_CHIPSET_ID")
    }

    // drm/panfrost_drm.h
    @Test
    fun testPanfrost_drm_h() {
        assertEquals<Int>(0, DRM_PANFROST_SUBMIT)
        logLine("DRM_PANFROST_SUBMIT=$DRM_PANFROST_SUBMIT")
    }

    // drm/qxl_drm.h
    @Test
    fun testQxl_drm_h() {
        assertEquals<Int>(0, QXL_GEM_DOMAIN_CPU)
        logLine("QXL_GEM_DOMAIN_CPU=$QXL_GEM_DOMAIN_CPU")
    }

    // drm/radeon_drm.h
    @Test
    fun testRadeon_drm_h() {
        assertEquals<Int>(0x00000001, RADEON_UPLOAD_CONTEXT)
        logLine("RADEON_UPLOAD_CONTEXT=$RADEON_UPLOAD_CONTEXT")
    }

    // drm/savage_drm.h
    @Test
    fun testSavage_drm_h() {
        assertEquals<Int>(0, SAVAGE_CARD_HEAP)
        logLine("SAVAGE_CARD_HEAP=$SAVAGE_CARD_HEAP")
    }

    // drm/sis_drm.h
    @Test
    fun testSis_drm_h() {
        assertEquals<Int>(0x04, DRM_SIS_FB_ALLOC)
        logLine("DRM_SIS_FB_ALLOC=$DRM_SIS_FB_ALLOC")
    }

    // drm/tegra_drm.h
    @Test
    fun testTegra_drm_h() {
        assertEquals<Int>(1, DRM_TEGRA_GEM_CREATE_TILED)
        logLine("DRM_TEGRA_GEM_CREATE_TILED=$DRM_TEGRA_GEM_CREATE_TILED")
    }

    // drm/v3d_drm.h
    @Test
    fun testV3d_drm_h() {
        assertEquals<Int>(0, DRM_V3D_SUBMIT_CL)
        logLine("DRM_V3D_SUBMIT_CL=$DRM_V3D_SUBMIT_CL")
    }

    // drm/vc4_drm.h
    @Test
    fun testVc4_drm_h() {
        assertEquals<Int>(0, DRM_VC4_SUBMIT_CL)
        logLine("DRM_VC4_SUBMIT_CL=$DRM_VC4_SUBMIT_CL")
    }

    // drm/vgem_drm.h
    @Test
    fun testVgem_drm_h() {
        assertEquals<Int>(0x1, DRM_VGEM_FENCE_ATTACH)
        logLine("DRM_VGEM_FENCE_ATTACH=$DRM_VGEM_FENCE_ATTACH")
    }

    // drm/via_drm.h
    @Test
    fun testVia_drm_h() {
        assertEquals<Int>(8, VIA_NR_SAREA_CLIPRECTS)
        logLine("VIA_NR_SAREA_CLIPRECTS=$VIA_NR_SAREA_CLIPRECTS")
    }

    // drm/virtgpu_drm.h
    @Test
    fun testVirtgpu_drm_h() {
        assertEquals<Int>(0x01, DRM_VIRTGPU_MAP)
        logLine("DRM_VIRTGPU_MAP=$DRM_VIRTGPU_MAP")
    }

    // drm/vmwgfx_drm.h
    @Test
    fun testVmwgfx_drm_h() {
        assertEquals<Int>(6, DRM_VMW_MAX_SURFACE_FACES)
        logLine("DRM_VMW_MAX_SURFACE_FACES=$DRM_VMW_MAX_SURFACE_FACES")
    }
}
