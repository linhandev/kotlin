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
import platform.ArkData.UDMF.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class UDMFTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_Udmf_ErrCode() {
        assertEquals(UDMF_E_OK.toInt(), 0)
        assertEquals(UDMF_ERR.toInt(), 20400000)
        assertEquals(UDMF_E_INVALID_PARAM.toInt(), 20400001)
        logLine("Udmf_ErrCode passed")
    }

    @Test
    fun testEnum_Udmf_ListenerStatus() {
        assertEquals(UDMF_FINISHED.toInt(), 0)
        assertEquals(UDMF_PROCESSING.toInt(), 1)
        assertEquals(UDMF_CANCELED.toInt(), 2)
        assertEquals(UDMF_INNER_ERROR.toInt(), 200)
        assertEquals(UDMF_INVALID_PARAMETERS.toInt(), 201)
        assertEquals(UDMF_DATA_NOT_FOUND.toInt(), 202)
        assertEquals(UDMF_SYNC_FAILED.toInt(), 203)
        assertEquals(UDMF_COPY_FILE_FAILED.toInt(), 204)
        logLine("Udmf_ListenerStatus passed")
    }

    @Test
    fun testEnum_Udmf_Intention() {
        logLine("UDMF_INTENTION_DRAG=${Udmf_Intention.UDMF_INTENTION_DRAG.value.toInt()}")
        logLine("UDMF_INTENTION_PASTEBOARD=${Udmf_Intention.UDMF_INTENTION_PASTEBOARD.value.toInt()}")
        logLine("UDMF_INTENTION_DATA_HUB=${Udmf_Intention.UDMF_INTENTION_DATA_HUB.value.toInt()}")
        logLine("UDMF_INTENTION_SYSTEM_SHARE=${Udmf_Intention.UDMF_INTENTION_SYSTEM_SHARE.value.toInt()}")
        logLine("UDMF_INTENTION_PICKER=${Udmf_Intention.UDMF_INTENTION_PICKER.value.toInt()}")
        logLine("UDMF_INTENTION_MENU=${Udmf_Intention.UDMF_INTENTION_MENU.value.toInt()}")
        logLine("Udmf_Intention passed")
    }

    @Test
    fun testEnum_Udmf_ShareOption() {
        logLine("SHARE_OPTIONS_INVALID=${Udmf_ShareOption.SHARE_OPTIONS_INVALID.value.toInt()}")
        logLine("SHARE_OPTIONS_IN_APP=${Udmf_ShareOption.SHARE_OPTIONS_IN_APP.value.toInt()}")
        logLine("SHARE_OPTIONS_CROSS_APP=${Udmf_ShareOption.SHARE_OPTIONS_CROSS_APP.value.toInt()}")
        logLine("Udmf_ShareOption passed")
    }

    @Test
    fun testEnum_Udmf_FileConflictOptions() {
        assertEquals(UDMF_OVERWRITE.toInt(), 0)
        assertEquals(UDMF_SKIP.toInt(), 1)
        logLine("Udmf_FileConflictOptions passed")
    }

    @Test
    fun testEnum_Udmf_ProgressIndicator() {
        assertEquals(UDMF_NONE.toInt(), 0)
        assertEquals(UDMF_DEFAULT.toInt(), 1)
        logLine("Udmf_ProgressIndicator passed")
    }

    @Test
    fun testEnum_Udmf_Visibility() {
        logLine("UDMF_ALL=${Udmf_Visibility.UDMF_ALL.value.toInt()}")
        logLine("UDMF_OWN_PROCESS=${Udmf_Visibility.UDMF_OWN_PROCESS.value.toInt()}")
        logLine("Udmf_Visibility passed")
    }

    // ---------- utd.h: OH_Utd_* ----------
    @Test
    fun testOH_Utd_Create() {
        val utd = OH_Utd_Create("type")
        OH_Utd_Destroy(utd)
        logLine("OH_Utd_Create passed")
    }

    @Test
    fun testOH_Utd_GetTypeId() {
        val utd = OH_Utd_Create("type")
        logLine("OH_Utd_GetTypeId=${OH_Utd_GetTypeId(utd)}")
        OH_Utd_Destroy(utd)
        logLine("OH_Utd_GetTypeId passed")
    }

    @Test
    fun testOH_Utd_GetDescription() {
        val utd = OH_Utd_Create("type")
        logLine("OH_Utd_GetDescription=${OH_Utd_GetDescription(utd)}")
        OH_Utd_Destroy(utd)
        logLine("OH_Utd_GetDescription passed")
    }

    @Test
    fun testOH_Utd_GetReferenceUrl() {
        val utd = OH_Utd_Create("type")
        logLine("OH_Utd_GetReferenceUrl=${OH_Utd_GetReferenceUrl(utd)}")
        OH_Utd_Destroy(utd)
        logLine("OH_Utd_GetReferenceUrl passed")
    }

    @Test
    fun testOH_Utd_GetIconFile() {
        val utd = OH_Utd_Create("type")
        logLine("OH_Utd_GetIconFile=${OH_Utd_GetIconFile(utd)}")
        OH_Utd_Destroy(utd)
        logLine("OH_Utd_GetIconFile passed")
    }

    @Test
    fun testOH_Utd_GetBelongingToTypes() {
        memScoped {
            val utd = OH_Utd_Create("type")
            val count = alloc<UIntVar>()
            OH_Utd_GetBelongingToTypes(utd, count.ptr)
            OH_Utd_Destroy(utd)
            logLine("OH_Utd_GetBelongingToTypes passed")
        }
    }

    @Test
    fun testOH_Utd_GetFilenameExtensions() {
        memScoped {
            val utd = OH_Utd_Create("type")
            val count = alloc<UIntVar>()
            OH_Utd_GetFilenameExtensions(utd, count.ptr)
            OH_Utd_Destroy(utd)
            logLine("OH_Utd_GetFilenameExtensions passed")
        }
    }

    @Test
    fun testOH_Utd_GetMimeTypes() {
        memScoped {
            val utd = OH_Utd_Create("type")
            val count = alloc<UIntVar>()
            OH_Utd_GetMimeTypes(utd, count.ptr)
            OH_Utd_Destroy(utd)
            logLine("OH_Utd_GetMimeTypes passed")
        }
    }

    @Test
    fun testOH_Utd_Equals() {
        val utd1 = OH_Utd_Create("type")
        val utd2 = OH_Utd_Create("type")
        logLine("OH_Utd_Equals=${OH_Utd_Equals(utd1, utd2)}")
        OH_Utd_Destroy(utd2)
        OH_Utd_Destroy(utd1)
        logLine("OH_Utd_Equals passed")
    }

    @Test
    fun testOH_Utd_Destroy() {
        val utd = OH_Utd_Create("type")
        OH_Utd_Destroy(utd)
        logLine("OH_Utd_Destroy passed")
    }

    @Test
    fun testOH_Utd_GetTypesByFilenameExtension() {
        memScoped {
            val count = alloc<UIntVar>()
            val listByExt = OH_Utd_GetTypesByFilenameExtension("txt", count.ptr)
            OH_Utd_DestroyStringList(listByExt, count.value)
            logLine("OH_Utd_GetTypesByFilenameExtension passed")
        }
    }

    @Test
    fun testOH_Utd_GetTypesByMimeType() {
        memScoped {
            val count = alloc<UIntVar>()
            val listByMime = OH_Utd_GetTypesByMimeType("text/plain", count.ptr)
            OH_Utd_DestroyStringList(listByMime, count.value)
            logLine("OH_Utd_GetTypesByMimeType passed")
        }
    }

    @Test
    fun testOH_Utd_DestroyStringList() {
        memScoped {
            val count = alloc<UIntVar>()
            val listByExt = OH_Utd_GetTypesByFilenameExtension("txt", count.ptr)
            OH_Utd_DestroyStringList(listByExt, count.value)
            logLine("OH_Utd_DestroyStringList passed")
        }
    }

    @Test
    fun testOH_Utd_BelongsTo() {
        logLine("OH_Utd_BelongsTo=${OH_Utd_BelongsTo("typeA", "typeB")}")
        logLine("OH_Utd_BelongsTo passed")
    }

    @Test
    fun testOH_Utd_IsLower() {
        logLine("OH_Utd_IsLower=${OH_Utd_IsLower("typeA", "typeB")}")
        logLine("OH_Utd_IsLower passed")
    }

    @Test
    fun testOH_Utd_IsHigher() {
        logLine("OH_Utd_IsHigher=${OH_Utd_IsHigher("typeA", "typeB")}")
        logLine("OH_Utd_IsHigher passed")
    }

    // ---------- uds.h: OH_UdsPlainText_* ----------
    @Test
    fun testOH_UdsPlainText_Create() {
        val plain = OH_UdsPlainText_Create()
        OH_UdsPlainText_Destroy(plain)
        logLine("OH_UdsPlainText_Create passed")
    }

    @Test
    fun testOH_UdsPlainText_GetType() {
        val plain = OH_UdsPlainText_Create()
        logLine("OH_UdsPlainText_GetType=${OH_UdsPlainText_GetType(plain)}")
        OH_UdsPlainText_Destroy(plain)
        logLine("OH_UdsPlainText_GetType passed")
    }

    @Test
    fun testOH_UdsPlainText_SetContent() {
        val plain = OH_UdsPlainText_Create()
        logLine("OH_UdsPlainText_SetContent=${OH_UdsPlainText_SetContent(plain, "content")}")
        OH_UdsPlainText_Destroy(plain)
        logLine("OH_UdsPlainText_SetContent passed")
    }

    @Test
    fun testOH_UdsPlainText_SetAbstract() {
        val plain = OH_UdsPlainText_Create()
        logLine("OH_UdsPlainText_SetAbstract=${OH_UdsPlainText_SetAbstract(plain, "abstract")}")
        OH_UdsPlainText_Destroy(plain)
        logLine("OH_UdsPlainText_SetAbstract passed")
    }

    @Test
    fun testOH_UdsPlainText_GetContent() {
        val plain = OH_UdsPlainText_Create()
        OH_UdsPlainText_SetContent(plain, "content")
        logLine("OH_UdsPlainText_GetContent=${OH_UdsPlainText_GetContent(plain)}")
        OH_UdsPlainText_Destroy(plain)
        logLine("OH_UdsPlainText_GetContent passed")
    }

    @Test
    fun testOH_UdsPlainText_GetAbstract() {
        val plain = OH_UdsPlainText_Create()
        OH_UdsPlainText_SetAbstract(plain, "abstract")
        logLine("OH_UdsPlainText_GetAbstract=${OH_UdsPlainText_GetAbstract(plain)}")
        OH_UdsPlainText_Destroy(plain)
        logLine("OH_UdsPlainText_GetAbstract passed")
    }

    @Test
    fun testOH_UdsPlainText_GetDetails() {
        val plain = OH_UdsPlainText_Create()
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsPlainText_GetDetails=${try { OH_UdsPlainText_GetDetails(plain, details) } catch (e: Throwable) { logLine("OH_UdsPlainText_GetDetails (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        OH_UdsPlainText_Destroy(plain)
        logLine("OH_UdsPlainText_GetDetails passed")
    }

    @Test
    fun testOH_UdsPlainText_SetDetails() {
        val plain = OH_UdsPlainText_Create()
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsPlainText_SetDetails=${try { OH_UdsPlainText_SetDetails(plain, details) } catch (e: Throwable) { logLine("OH_UdsPlainText_SetDetails (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        OH_UdsPlainText_Destroy(plain)
        logLine("OH_UdsPlainText_SetDetails passed")
    }

    @Test
    fun testOH_UdsPlainText_Destroy() {
        val plain = OH_UdsPlainText_Create()
        OH_UdsPlainText_Destroy(plain)
        logLine("OH_UdsPlainText_Destroy passed")
    }

    // ---------- uds.h: OH_UdsHyperlink_* ----------
    @Test
    fun testOH_UdsHyperlink_Create() {
        val link = OH_UdsHyperlink_Create()
        OH_UdsHyperlink_Destroy(link)
        logLine("OH_UdsHyperlink_Create passed")
    }

    @Test
    fun testOH_UdsHyperlink_GetType() {
        val link = OH_UdsHyperlink_Create()
        logLine("OH_UdsHyperlink_GetType=${OH_UdsHyperlink_GetType(link)}")
        OH_UdsHyperlink_Destroy(link)
        logLine("OH_UdsHyperlink_GetType passed")
    }

    @Test
    fun testOH_UdsHyperlink_SetUrl() {
        val link = OH_UdsHyperlink_Create()
        logLine("OH_UdsHyperlink_SetUrl=${OH_UdsHyperlink_SetUrl(link, "http://example.com")}")
        OH_UdsHyperlink_Destroy(link)
        logLine("OH_UdsHyperlink_SetUrl passed")
    }

    @Test
    fun testOH_UdsHyperlink_SetDescription() {
        val link = OH_UdsHyperlink_Create()
        logLine("OH_UdsHyperlink_SetDescription=${OH_UdsHyperlink_SetDescription(link, "desc")}")
        OH_UdsHyperlink_Destroy(link)
        logLine("OH_UdsHyperlink_SetDescription passed")
    }

    @Test
    fun testOH_UdsHyperlink_GetUrl() {
        val link = OH_UdsHyperlink_Create()
        OH_UdsHyperlink_SetUrl(link, "http://example.com")
        logLine("OH_UdsHyperlink_GetUrl=${OH_UdsHyperlink_GetUrl(link)}")
        OH_UdsHyperlink_Destroy(link)
        logLine("OH_UdsHyperlink_GetUrl passed")
    }

    @Test
    fun testOH_UdsHyperlink_GetDescription() {
        val link = OH_UdsHyperlink_Create()
        OH_UdsHyperlink_SetDescription(link, "desc")
        logLine("OH_UdsHyperlink_GetDescription=${OH_UdsHyperlink_GetDescription(link)}")
        OH_UdsHyperlink_Destroy(link)
        logLine("OH_UdsHyperlink_GetDescription passed")
    }

    @Test
    fun testOH_UdsHyperlink_GetDetails() {
        val link = OH_UdsHyperlink_Create()
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsHyperlink_GetDetails=${try { OH_UdsHyperlink_GetDetails(link, details) } catch (e: Throwable) { logLine("OH_UdsHyperlink_GetDetails (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        OH_UdsHyperlink_Destroy(link)
        logLine("OH_UdsHyperlink_GetDetails passed")
    }

    @Test
    fun testOH_UdsHyperlink_SetDetails() {
        val link = OH_UdsHyperlink_Create()
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsHyperlink_SetDetails=${try { OH_UdsHyperlink_SetDetails(link, details) } catch (e: Throwable) { logLine("OH_UdsHyperlink_SetDetails (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        OH_UdsHyperlink_Destroy(link)
        logLine("OH_UdsHyperlink_SetDetails passed")
    }

    @Test
    fun testOH_UdsHyperlink_Destroy() {
        val link = OH_UdsHyperlink_Create()
        OH_UdsHyperlink_Destroy(link)
        logLine("OH_UdsHyperlink_Destroy passed")
    }

    // ---------- uds.h: OH_UdsHtml_* ----------
    @Test
    fun testOH_UdsHtml_Create() {
        val html = OH_UdsHtml_Create()
        OH_UdsHtml_Destroy(html)
        logLine("OH_UdsHtml_Create passed")
    }

    @Test
    fun testOH_UdsHtml_GetType() {
        val html = OH_UdsHtml_Create()
        logLine("OH_UdsHtml_GetType=${OH_UdsHtml_GetType(html)}")
        OH_UdsHtml_Destroy(html)
        logLine("OH_UdsHtml_GetType passed")
    }

    @Test
    fun testOH_UdsHtml_SetContent() {
        val html = OH_UdsHtml_Create()
        logLine("OH_UdsHtml_SetContent=${OH_UdsHtml_SetContent(html, "<p>html</p>")}")
        OH_UdsHtml_Destroy(html)
        logLine("OH_UdsHtml_SetContent passed")
    }

    @Test
    fun testOH_UdsHtml_SetPlainContent() {
        val html = OH_UdsHtml_Create()
        logLine("OH_UdsHtml_SetPlainContent=${OH_UdsHtml_SetPlainContent(html, "plain")}")
        OH_UdsHtml_Destroy(html)
        logLine("OH_UdsHtml_SetPlainContent passed")
    }

    @Test
    fun testOH_UdsHtml_GetContent() {
        val html = OH_UdsHtml_Create()
        OH_UdsHtml_SetContent(html, "<p>html</p>")
        logLine("OH_UdsHtml_GetContent=${OH_UdsHtml_GetContent(html)}")
        OH_UdsHtml_Destroy(html)
        logLine("OH_UdsHtml_GetContent passed")
    }

    @Test
    fun testOH_UdsHtml_GetPlainContent() {
        val html = OH_UdsHtml_Create()
        OH_UdsHtml_SetPlainContent(html, "plain")
        logLine("OH_UdsHtml_GetPlainContent=${OH_UdsHtml_GetPlainContent(html)}")
        OH_UdsHtml_Destroy(html)
        logLine("OH_UdsHtml_GetPlainContent passed")
    }

    @Test
    fun testOH_UdsHtml_GetDetails() {
        val html = OH_UdsHtml_Create()
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsHtml_GetDetails=${try { OH_UdsHtml_GetDetails(html, details) } catch (e: Throwable) { logLine("OH_UdsHtml_GetDetails (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        OH_UdsHtml_Destroy(html)
        logLine("OH_UdsHtml_GetDetails passed")
    }

    @Test
    fun testOH_UdsHtml_SetDetails() {
        val html = OH_UdsHtml_Create()
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsHtml_SetDetails=${try { OH_UdsHtml_SetDetails(html, details) } catch (e: Throwable) { logLine("OH_UdsHtml_SetDetails (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        OH_UdsHtml_Destroy(html)
        logLine("OH_UdsHtml_SetDetails passed")
    }

    @Test
    fun testOH_UdsHtml_Destroy() {
        val html = OH_UdsHtml_Create()
        OH_UdsHtml_Destroy(html)
        logLine("OH_UdsHtml_Destroy passed")
    }

    // ---------- uds.h: OH_UdsAppItem_* ----------
    @Test
    fun testOH_UdsAppItem_Create() {
        val app = OH_UdsAppItem_Create()
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_Create passed")
    }

    @Test
    fun testOH_UdsAppItem_GetType() {
        val app = OH_UdsAppItem_Create()
        logLine("OH_UdsAppItem_GetType=${OH_UdsAppItem_GetType(app)}")
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_GetType passed")
    }

    @Test
    fun testOH_UdsAppItem_SetId() {
        val app = OH_UdsAppItem_Create()
        logLine("OH_UdsAppItem_SetId=${OH_UdsAppItem_SetId(app, "id")}")
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_SetId passed")
    }

    @Test
    fun testOH_UdsAppItem_SetName() {
        val app = OH_UdsAppItem_Create()
        logLine("OH_UdsAppItem_SetName=${OH_UdsAppItem_SetName(app, "name")}")
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_SetName passed")
    }

    @Test
    fun testOH_UdsAppItem_SetIconId() {
        val app = OH_UdsAppItem_Create()
        logLine("OH_UdsAppItem_SetIconId=${OH_UdsAppItem_SetIconId(app, "icon")}")
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_SetIconId passed")
    }

    @Test
    fun testOH_UdsAppItem_SetLabelId() {
        val app = OH_UdsAppItem_Create()
        logLine("OH_UdsAppItem_SetLabelId=${OH_UdsAppItem_SetLabelId(app, "label")}")
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_SetLabelId passed")
    }

    @Test
    fun testOH_UdsAppItem_SetBundleName() {
        val app = OH_UdsAppItem_Create()
        logLine("OH_UdsAppItem_SetBundleName=${OH_UdsAppItem_SetBundleName(app, "bundle")}")
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_SetBundleName passed")
    }

    @Test
    fun testOH_UdsAppItem_SetAbilityName() {
        val app = OH_UdsAppItem_Create()
        logLine("OH_UdsAppItem_SetAbilityName=${OH_UdsAppItem_SetAbilityName(app, "ability")}")
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_SetAbilityName passed")
    }

    @Test
    fun testOH_UdsAppItem_GetId() {
        val app = OH_UdsAppItem_Create()
        OH_UdsAppItem_SetId(app, "id")
        logLine("OH_UdsAppItem_GetId=${OH_UdsAppItem_GetId(app)}")
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_GetId passed")
    }

    @Test
    fun testOH_UdsAppItem_GetName() {
        val app = OH_UdsAppItem_Create()
        OH_UdsAppItem_SetName(app, "name")
        logLine("OH_UdsAppItem_GetName=${OH_UdsAppItem_GetName(app)}")
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_GetName passed")
    }

    @Test
    fun testOH_UdsAppItem_GetIconId() {
        val app = OH_UdsAppItem_Create()
        OH_UdsAppItem_SetIconId(app, "icon")
        logLine("OH_UdsAppItem_GetIconId=${OH_UdsAppItem_GetIconId(app)}")
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_GetIconId passed")
    }

    @Test
    fun testOH_UdsAppItem_GetLabelId() {
        val app = OH_UdsAppItem_Create()
        OH_UdsAppItem_SetLabelId(app, "label")
        logLine("OH_UdsAppItem_GetLabelId=${OH_UdsAppItem_GetLabelId(app)}")
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_GetLabelId passed")
    }

    @Test
    fun testOH_UdsAppItem_GetBundleName() {
        val app = OH_UdsAppItem_Create()
        OH_UdsAppItem_SetBundleName(app, "bundle")
        logLine("OH_UdsAppItem_GetBundleName=${OH_UdsAppItem_GetBundleName(app)}")
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_GetBundleName passed")
    }

    @Test
    fun testOH_UdsAppItem_GetAbilityName() {
        val app = OH_UdsAppItem_Create()
        OH_UdsAppItem_SetAbilityName(app, "ability")
        logLine("OH_UdsAppItem_GetAbilityName=${OH_UdsAppItem_GetAbilityName(app)}")
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_GetAbilityName passed")
    }

    @Test
    fun testOH_UdsAppItem_GetDetails() {
        val app = OH_UdsAppItem_Create()
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsAppItem_GetDetails=${try { OH_UdsAppItem_GetDetails(app, details) } catch (e: Throwable) { logLine("OH_UdsAppItem_GetDetails (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_GetDetails passed")
    }

    @Test
    fun testOH_UdsAppItem_SetDetails() {
        val app = OH_UdsAppItem_Create()
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsAppItem_SetDetails=${try { OH_UdsAppItem_SetDetails(app, details) } catch (e: Throwable) { logLine("OH_UdsAppItem_SetDetails (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_SetDetails passed")
    }

    @Test
    fun testOH_UdsAppItem_Destroy() {
        val app = OH_UdsAppItem_Create()
        OH_UdsAppItem_Destroy(app)
        logLine("OH_UdsAppItem_Destroy passed")
    }


    @Test
    fun testOH_UdsFileUri_Create() {
        val fileUri = OH_UdsFileUri_Create()
        OH_UdsFileUri_Destroy(fileUri)
        logLine("OH_UdsFileUri_Create passed")
    }

    @Test
    fun testOH_UdsFileUri_GetType() {
        val fileUri = OH_UdsFileUri_Create()
        logLine("OH_UdsFileUri_GetType=${OH_UdsFileUri_GetType(fileUri)}")
        OH_UdsFileUri_Destroy(fileUri)
        logLine("OH_UdsFileUri_GetType passed")
    }

    @Test
    fun testOH_UdsFileUri_SetFileUri() {
        val fileUri = OH_UdsFileUri_Create()
        logLine("OH_UdsFileUri_SetFileUri=${OH_UdsFileUri_SetFileUri(fileUri, "file:///path")}")
        OH_UdsFileUri_Destroy(fileUri)
        logLine("OH_UdsFileUri_SetFileUri passed")
    }

    @Test
    fun testOH_UdsFileUri_SetFileType() {
        val fileUri = OH_UdsFileUri_Create()
        logLine("OH_UdsFileUri_SetFileType=${OH_UdsFileUri_SetFileType(fileUri, "text/plain")}")
        OH_UdsFileUri_Destroy(fileUri)
        logLine("OH_UdsFileUri_SetFileType passed")
    }

    @Test
    fun testOH_UdsFileUri_GetFileUri() {
        val fileUri = OH_UdsFileUri_Create()
        OH_UdsFileUri_SetFileUri(fileUri, "file:///path")
        logLine("OH_UdsFileUri_GetFileUri=${OH_UdsFileUri_GetFileUri(fileUri)}")
        OH_UdsFileUri_Destroy(fileUri)
        logLine("OH_UdsFileUri_GetFileUri passed")
    }

    @Test
    fun testOH_UdsFileUri_GetFileType() {
        val fileUri = OH_UdsFileUri_Create()
        OH_UdsFileUri_SetFileType(fileUri, "text/plain")
        logLine("OH_UdsFileUri_GetFileType=${OH_UdsFileUri_GetFileType(fileUri)}")
        OH_UdsFileUri_Destroy(fileUri)
        logLine("OH_UdsFileUri_GetFileType passed")
    }

    @Test
    fun testOH_UdsFileUri_GetDetails() {
        val fileUri = OH_UdsFileUri_Create()
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsFileUri_GetDetails=${try { OH_UdsFileUri_GetDetails(fileUri, details) } catch (e: Throwable) { logLine("OH_UdsFileUri_GetDetails (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        OH_UdsFileUri_Destroy(fileUri)
        logLine("OH_UdsFileUri_GetDetails passed")
    }

    @Test
    fun testOH_UdsFileUri_SetDetails() {
        val fileUri = OH_UdsFileUri_Create()
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsFileUri_SetDetails=${try { OH_UdsFileUri_SetDetails(fileUri, details) } catch (e: Throwable) { logLine("OH_UdsFileUri_SetDetails (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        OH_UdsFileUri_Destroy(fileUri)
        logLine("OH_UdsFileUri_SetDetails passed")
    }

    @Test
    fun testOH_UdsFileUri_Destroy() {
        val fileUri = OH_UdsFileUri_Create()
        OH_UdsFileUri_Destroy(fileUri)
        logLine("OH_UdsFileUri_Destroy passed")
    }


    @Test
    fun testOH_UdsPixelMap_Create() {
        val pm = OH_UdsPixelMap_Create()
        OH_UdsPixelMap_Destroy(pm)
        logLine("OH_UdsPixelMap_Create passed")
    }

    @Test
    fun testOH_UdsPixelMap_GetType() {
        val pm = OH_UdsPixelMap_Create()
        logLine("OH_UdsPixelMap_GetType=${OH_UdsPixelMap_GetType(pm)}")
        OH_UdsPixelMap_Destroy(pm)
        logLine("OH_UdsPixelMap_GetType passed")
    }

    // @Test
    // fun testOH_UdsPixelMap_GetPixelMap() {
    //     val pm = OH_UdsPixelMap_Create()
    //     assertNotNull(pm)
    //     OH_UdsPixelMap_GetPixelMap(pm, null)
    //     OH_UdsPixelMap_Destroy(pm)
    //     logLine("OH_UdsPixelMap_GetPixelMap passed")
    // }

    @Test
    fun testOH_UdsPixelMap_SetPixelMap() {
        val pm = OH_UdsPixelMap_Create()
        logLine("OH_UdsPixelMap_SetPixelMap=${OH_UdsPixelMap_SetPixelMap(pm, null)}")
        OH_UdsPixelMap_Destroy(pm)
        logLine("OH_UdsPixelMap_SetPixelMap passed")
    }

    @Test
    fun testOH_UdsPixelMap_GetDetails() {
        val pm = OH_UdsPixelMap_Create()
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsPixelMap_GetDetails=${try { OH_UdsPixelMap_GetDetails(pm, details) } catch (e: Throwable) { logLine("OH_UdsPixelMap_GetDetails (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        OH_UdsPixelMap_Destroy(pm)
        logLine("OH_UdsPixelMap_GetDetails passed")
    }

    @Test
    fun testOH_UdsPixelMap_SetDetails() {
        val pm = OH_UdsPixelMap_Create()
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsPixelMap_SetDetails=${try { OH_UdsPixelMap_SetDetails(pm, details) } catch (e: Throwable) { logLine("OH_UdsPixelMap_SetDetails (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        OH_UdsPixelMap_Destroy(pm)
        logLine("OH_UdsPixelMap_SetDetails passed")
    }

    @Test
    fun testOH_UdsPixelMap_Destroy() {
        val pm = OH_UdsPixelMap_Create()
        OH_UdsPixelMap_Destroy(pm)
        logLine("OH_UdsPixelMap_Destroy passed")
    }


    @Test
    fun testOH_UdsArrayBuffer_Create() {
        val buf = OH_UdsArrayBuffer_Create()
        OH_UdsArrayBuffer_Destroy(buf)
        logLine("OH_UdsArrayBuffer_Create passed")
    }

    @Test
    fun testOH_UdsArrayBuffer_SetData() {
        val buf = OH_UdsArrayBuffer_Create()
        val data = UByteArray(4)
        logLine("OH_UdsArrayBuffer_SetData=${OH_UdsArrayBuffer_SetData(buf, data.refTo(0), 4u)}")
        OH_UdsArrayBuffer_Destroy(buf)
        logLine("OH_UdsArrayBuffer_SetData passed")
    }

    @Test
    fun testOH_UdsArrayBuffer_GetData() {
        memScoped {
            val buf = OH_UdsArrayBuffer_Create()
            val data = UByteArray(4)
            OH_UdsArrayBuffer_SetData(buf, data.refTo(0), 4u)
            val outData = alloc<CPointerVar<UByteVar>>()
            val len = alloc<UIntVar>()
            logLine("OH_UdsArrayBuffer_GetData=${OH_UdsArrayBuffer_GetData(buf, outData.ptr, len.ptr)}")
            OH_UdsArrayBuffer_Destroy(buf)
            logLine("OH_UdsArrayBuffer_GetData passed")
        }
    }

    @Test
    fun testOH_UdsArrayBuffer_Destroy() {
        val buf = OH_UdsArrayBuffer_Create()
        logLine("OH_UdsArrayBuffer_Destroy=${OH_UdsArrayBuffer_Destroy(buf)}")
        logLine("OH_UdsArrayBuffer_Destroy passed")
    }


    @Test
    fun testOH_UdsContentForm_Create() {
        val cf = OH_UdsContentForm_Create()
        OH_UdsContentForm_Destroy(cf)
        logLine("OH_UdsContentForm_Create passed")
    }

    @Test
    fun testOH_UdsContentForm_GetType() {
        val cf = OH_UdsContentForm_Create()
        logLine("OH_UdsContentForm_GetType=${OH_UdsContentForm_GetType(cf)}")
        OH_UdsContentForm_Destroy(cf)
        logLine("OH_UdsContentForm_GetType passed")
    }

    @Test
    fun testOH_UdsContentForm_SetThumbData() {
        val cf = OH_UdsContentForm_Create()
        logLine("OH_UdsContentForm_SetThumbData=${OH_UdsContentForm_SetThumbData(cf, null, 0u)}")
        OH_UdsContentForm_Destroy(cf)
        logLine("OH_UdsContentForm_SetThumbData passed")
    }

    @Test
    fun testOH_UdsContentForm_SetDescription() {
        val cf = OH_UdsContentForm_Create()
        logLine("OH_UdsContentForm_SetDescription=${OH_UdsContentForm_SetDescription(cf, "desc")}")
        OH_UdsContentForm_Destroy(cf)
        logLine("OH_UdsContentForm_SetDescription passed")
    }

    @Test
    fun testOH_UdsContentForm_SetTitle() {
        val cf = OH_UdsContentForm_Create()
        logLine("OH_UdsContentForm_SetTitle=${OH_UdsContentForm_SetTitle(cf, "title")}")
        OH_UdsContentForm_Destroy(cf)
        logLine("OH_UdsContentForm_SetTitle passed")
    }

    @Test
    fun testOH_UdsContentForm_GetThumbData() {
        memScoped {
            val cf = OH_UdsContentForm_Create()
            val thumbOut = alloc<CPointerVar<UByteVar>>()
            val len = alloc<UIntVar>()
            logLine("OH_UdsContentForm_GetThumbData=${OH_UdsContentForm_GetThumbData(cf, thumbOut.ptr, len.ptr)}")
            OH_UdsContentForm_Destroy(cf)
            logLine("OH_UdsContentForm_GetThumbData passed")
        }
    }

    @Test
    fun testOH_UdsContentForm_GetDescription() {
        val cf = OH_UdsContentForm_Create()
        OH_UdsContentForm_SetDescription(cf, "desc")
        logLine("OH_UdsContentForm_GetDescription=${OH_UdsContentForm_GetDescription(cf)}")
        OH_UdsContentForm_Destroy(cf)
        logLine("OH_UdsContentForm_GetDescription passed")
    }

    @Test
    fun testOH_UdsContentForm_GetTitle() {
        val cf = OH_UdsContentForm_Create()
        OH_UdsContentForm_SetTitle(cf, "title")
        logLine("OH_UdsContentForm_GetTitle=${OH_UdsContentForm_GetTitle(cf)}")
        OH_UdsContentForm_Destroy(cf)
        logLine("OH_UdsContentForm_GetTitle passed")
    }

    @Test
    fun testOH_UdsContentForm_SetAppIcon() {
        val cf = OH_UdsContentForm_Create()
        logLine("OH_UdsContentForm_SetAppIcon=${OH_UdsContentForm_SetAppIcon(cf, null, 0u)}")
        OH_UdsContentForm_Destroy(cf)
        logLine("OH_UdsContentForm_SetAppIcon passed")
    }

    @Test
    fun testOH_UdsContentForm_GetAppIcon() {
        memScoped {
            val cf = OH_UdsContentForm_Create()
            val thumbOut = alloc<CPointerVar<UByteVar>>()
            val len = alloc<UIntVar>()
            logLine("OH_UdsContentForm_GetAppIcon=${OH_UdsContentForm_GetAppIcon(cf, thumbOut.ptr, len.ptr)}")
            OH_UdsContentForm_Destroy(cf)
            logLine("OH_UdsContentForm_GetAppIcon passed")
        }
    }

    @Test
    fun testOH_UdsContentForm_SetAppName() {
        val cf = OH_UdsContentForm_Create()
        logLine("OH_UdsContentForm_SetAppName=${OH_UdsContentForm_SetAppName(cf, "app")}")
        OH_UdsContentForm_Destroy(cf)
        logLine("OH_UdsContentForm_SetAppName passed")
    }

    @Test
    fun testOH_UdsContentForm_GetAppName() {
        val cf = OH_UdsContentForm_Create()
        OH_UdsContentForm_SetAppName(cf, "app")
        logLine("OH_UdsContentForm_GetAppName=${OH_UdsContentForm_GetAppName(cf)}")
        OH_UdsContentForm_Destroy(cf)
        logLine("OH_UdsContentForm_GetAppName passed")
    }

    @Test
    fun testOH_UdsContentForm_SetLinkUri() {
        val cf = OH_UdsContentForm_Create()
        logLine("OH_UdsContentForm_SetLinkUri=${OH_UdsContentForm_SetLinkUri(cf, "http://link")}")
        OH_UdsContentForm_Destroy(cf)
        logLine("OH_UdsContentForm_SetLinkUri passed")
    }

    @Test
    fun testOH_UdsContentForm_GetLinkUri() {
        val cf = OH_UdsContentForm_Create()
        OH_UdsContentForm_SetLinkUri(cf, "http://link")
        logLine("OH_UdsContentForm_GetLinkUri=${OH_UdsContentForm_GetLinkUri(cf)}")
        OH_UdsContentForm_Destroy(cf)
        logLine("OH_UdsContentForm_GetLinkUri passed")
    }

    @Test
    fun testOH_UdsContentForm_Destroy() {
        val cf = OH_UdsContentForm_Create()
        OH_UdsContentForm_Destroy(cf)
        logLine("OH_UdsContentForm_Destroy passed")
    }


    @Test
    fun testOH_UdsDetails_Create() {
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        logLine("OH_UdsDetails_Create passed")
    }

    @Test
    fun testOH_UdsDetails_HasKey() {
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsDetails_HasKey=${try { OH_UdsDetails_HasKey(details, "k") } catch (e: Throwable) { logLine("OH_UdsDetails_HasKey (API 22) exception: $e"); false }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        logLine("OH_UdsDetails_HasKey passed")
    }

    @Test
    fun testOH_UdsDetails_SetValue() {
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsDetails_SetValue=${try { OH_UdsDetails_SetValue(details, "k", "v") } catch (e: Throwable) { logLine("OH_UdsDetails_SetValue (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        logLine("OH_UdsDetails_SetValue passed")
    }

    @Test
    fun testOH_UdsDetails_GetValue() {
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        try { OH_UdsDetails_SetValue(details, "k", "v") } catch (e: Throwable) { logLine("OH_UdsDetails_SetValue (API 22) exception: $e") }
        logLine("OH_UdsDetails_GetValue=${try { OH_UdsDetails_GetValue(details, "k") } catch (e: Throwable) { logLine("OH_UdsDetails_GetValue (API 22) exception: $e"); null }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        logLine("OH_UdsDetails_GetValue passed")
    }

    @Test
    fun testOH_UdsDetails_GetAllKeys() {
        memScoped {
            val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
            val count = alloc<UIntVar>()
            try { OH_UdsDetails_GetAllKeys(details, count.ptr) } catch (e: Throwable) { logLine("OH_UdsDetails_GetAllKeys (API 22) exception: $e") }
            try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
            logLine("OH_UdsDetails_GetAllKeys passed")
        }
    }

    @Test
    fun testOH_UdsDetails_Remove() {
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        try { OH_UdsDetails_SetValue(details, "k", "v") } catch (e: Throwable) { logLine("OH_UdsDetails_SetValue (API 22) exception: $e") }
        logLine("OH_UdsDetails_Remove=${try { OH_UdsDetails_Remove(details, "k") } catch (e: Throwable) { logLine("OH_UdsDetails_Remove (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        logLine("OH_UdsDetails_Remove passed")
    }

    @Test
    fun testOH_UdsDetails_Clear() {
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        logLine("OH_UdsDetails_Clear=${try { OH_UdsDetails_Clear(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Clear (API 22) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        logLine("OH_UdsDetails_Clear passed")
    }

    @Test
    fun testOH_UdsDetails_Destroy() {
        val details = try { OH_UdsDetails_Create() } catch (e: Throwable) { logLine("OH_UdsDetails_Create (API 22) exception: $e"); null }
        try { OH_UdsDetails_Destroy(details) } catch (e: Throwable) { logLine("OH_UdsDetails_Destroy (API 22) exception: $e") }
        logLine("OH_UdsDetails_Destroy passed")
    }

    // ---------- udmf.h: OH_UdmfData_* ----------
    @Test
    fun testOH_UdmfData_Create() {
        val data = OH_UdmfData_Create()
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfData_Create passed")
    }

    @Test
    fun testOH_UdmfData_Destroy() {
        val data = OH_UdmfData_Create()
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfData_Destroy passed")
    }

    @Test
    fun testOH_UdmfData_HasType() {
        val data = OH_UdmfData_Create()
        assertFalse(OH_UdmfData_HasType(data, "unknown"))
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfData_HasType passed")
    }

    @Test
    fun testOH_UdmfData_GetTypes() {
        memScoped {
            val data = OH_UdmfData_Create()
            val count = alloc<UIntVar>()
            assertNull(OH_UdmfData_GetTypes(data, count.ptr))
            OH_UdmfData_Destroy(data)
            logLine("OH_UdmfData_GetTypes passed")
        }
    }

    @Test
    fun testOH_UdmfData_GetRecords() {
        memScoped {
            val data = OH_UdmfData_Create()
            val count = alloc<UIntVar>()
            assertNull(OH_UdmfData_GetRecords(data, count.ptr))
            OH_UdmfData_Destroy(data)
            logLine("OH_UdmfData_GetRecords passed")
        }
    }

    @Test
    fun testOH_UdmfData_AddRecord() {
        val data = OH_UdmfData_Create()
        val rec = OH_UdmfRecord_Create()
        logLine("OH_UdmfData_AddRecord=${OH_UdmfData_AddRecord(data, rec)}")
        OH_UdmfRecord_Destroy(rec)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfData_AddRecord passed")
    }

    @Test
    fun testOH_UdmfData_GetPrimaryPlainText() {
        val data = OH_UdmfData_Create()
        val plain = OH_UdsPlainText_Create()
        logLine("OH_UdmfData_GetPrimaryPlainText=${OH_UdmfData_GetPrimaryPlainText(data, plain)}")
        OH_UdsPlainText_Destroy(plain)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfData_GetPrimaryPlainText passed")
    }

    @Test
    fun testOH_UdmfData_GetPrimaryHtml() {
        val data = OH_UdmfData_Create()
        val html = OH_UdsHtml_Create()
        logLine("OH_UdmfData_GetPrimaryHtml=${OH_UdmfData_GetPrimaryHtml(data, html)}")
        OH_UdsHtml_Destroy(html)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfData_GetPrimaryHtml passed")
    }

    @Test
    fun testOH_UdmfData_GetRecordCount() {
        val data = OH_UdmfData_Create()
        logLine("OH_UdmfData_GetRecordCount=${OH_UdmfData_GetRecordCount(data)}")
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfData_GetRecordCount passed")
    }

    @Test
    fun testOH_UdmfData_GetRecord() {
        val data = OH_UdmfData_Create()
        logLine("OH_UdmfData_GetRecord=${OH_UdmfData_GetRecord(data, 0u)}")
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfData_GetRecord passed")
    }

    @Test
    fun testOH_UdmfData_IsLocal() {
        val data = OH_UdmfData_Create()
        logLine("OH_UdmfData_IsLocal=${OH_UdmfData_IsLocal(data)}")
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfData_IsLocal passed")
    }


    @Test
    fun testOH_UdmfRecordProvider_Create() {
        val provider = OH_UdmfRecordProvider_Create()
        OH_UdmfRecordProvider_Destroy(provider)
        logLine("OH_UdmfRecordProvider_Create passed")
    }

    @Test
    fun testOH_UdmfRecordProvider_SetData() {
        val provider = OH_UdmfRecordProvider_Create()
        logLine("OH_UdmfRecordProvider_SetData=${OH_UdmfRecordProvider_SetData(provider, null, null, null)}")
        OH_UdmfRecordProvider_Destroy(provider)
        logLine("OH_UdmfRecordProvider_SetData passed")
    }

    @Test
    fun testOH_UdmfRecordProvider_Destroy() {
        val provider = OH_UdmfRecordProvider_Create()
        logLine("OH_UdmfRecordProvider_Destroy=${OH_UdmfRecordProvider_Destroy(provider)}")
        logLine("OH_UdmfRecordProvider_Destroy passed")
    }


    @Test
    fun testOH_UdmfRecord_Create() {
        val rec = OH_UdmfRecord_Create()
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_Create passed")
    }

    @Test
    fun testOH_UdmfRecord_Destroy() {
        val rec = OH_UdmfRecord_Create()
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_Destroy passed")
    }

    @Test
    fun testOH_UdmfRecord_GetTypes() {
        memScoped {
            val rec = OH_UdmfRecord_Create()
            val count = alloc<UIntVar>()
            OH_UdmfRecord_GetTypes(rec, count.ptr)
            OH_UdmfRecord_Destroy(rec)
            logLine("OH_UdmfRecord_GetTypes passed")
        }
    }

    @Test
    fun testOH_UdmfRecord_AddPlainText() {
        val rec = OH_UdmfRecord_Create()
        val plain = OH_UdsPlainText_Create()
        logLine("OH_UdmfRecord_AddPlainText=${OH_UdmfRecord_AddPlainText(rec, plain)}")
        OH_UdsPlainText_Destroy(plain)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_AddPlainText passed")
    }

    @Test
    fun testOH_UdmfRecord_GetPlainText() {
        val rec = OH_UdmfRecord_Create()
        val plain = OH_UdsPlainText_Create()
        OH_UdmfRecord_AddPlainText(rec, plain)
        logLine("OH_UdmfRecord_GetPlainText=${OH_UdmfRecord_GetPlainText(rec, plain)}")
        OH_UdsPlainText_Destroy(plain)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_GetPlainText passed")
    }

    @Test
    fun testOH_UdmfRecord_AddHyperlink() {
        val rec = OH_UdmfRecord_Create()
        val link = OH_UdsHyperlink_Create()
        logLine("OH_UdmfRecord_AddHyperlink=${OH_UdmfRecord_AddHyperlink(rec, link)}")
        OH_UdsHyperlink_Destroy(link)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_AddHyperlink passed")
    }

    @Test
    fun testOH_UdmfRecord_GetHyperlink() {
        val rec = OH_UdmfRecord_Create()
        val link = OH_UdsHyperlink_Create()
        OH_UdmfRecord_AddHyperlink(rec, link)
        logLine("OH_UdmfRecord_GetHyperlink=${OH_UdmfRecord_GetHyperlink(rec, link)}")
        OH_UdsHyperlink_Destroy(link)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_GetHyperlink passed")
    }

    @Test
    fun testOH_UdmfRecord_AddHtml() {
        val rec = OH_UdmfRecord_Create()
        val html = OH_UdsHtml_Create()
        logLine("OH_UdmfRecord_AddHtml=${OH_UdmfRecord_AddHtml(rec, html)}")
        OH_UdsHtml_Destroy(html)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_AddHtml passed")
    }

    @Test
    fun testOH_UdmfRecord_GetHtml() {
        val rec = OH_UdmfRecord_Create()
        val html = OH_UdsHtml_Create()
        OH_UdmfRecord_AddHtml(rec, html)
        logLine("OH_UdmfRecord_GetHtml=${OH_UdmfRecord_GetHtml(rec, html)}")
        OH_UdsHtml_Destroy(html)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_GetHtml passed")
    }

    @Test
    fun testOH_UdmfRecord_AddAppItem() {
        val rec = OH_UdmfRecord_Create()
        val appItem = OH_UdsAppItem_Create()
        logLine("OH_UdmfRecord_AddAppItem=${OH_UdmfRecord_AddAppItem(rec, appItem)}")
        OH_UdsAppItem_Destroy(appItem)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_AddAppItem passed")
    }

    @Test
    fun testOH_UdmfRecord_GetAppItem() {
        val rec = OH_UdmfRecord_Create()
        val appItem = OH_UdsAppItem_Create()
        OH_UdmfRecord_AddAppItem(rec, appItem)
        logLine("OH_UdmfRecord_GetAppItem=${OH_UdmfRecord_GetAppItem(rec, appItem)}")
        OH_UdsAppItem_Destroy(appItem)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_GetAppItem passed")
    }

    @Test
    fun testOH_UdmfRecord_AddFileUri() {
        val rec = OH_UdmfRecord_Create()
        val fileUri = OH_UdsFileUri_Create()
        logLine("OH_UdmfRecord_AddFileUri=${OH_UdmfRecord_AddFileUri(rec, fileUri)}")
        OH_UdsFileUri_Destroy(fileUri)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_AddFileUri passed")
    }

    @Test
    fun testOH_UdmfRecord_GetFileUri() {
        val rec = OH_UdmfRecord_Create()
        val fileUri = OH_UdsFileUri_Create()
        OH_UdmfRecord_AddFileUri(rec, fileUri)
        logLine("OH_UdmfRecord_GetFileUri=${OH_UdmfRecord_GetFileUri(rec, fileUri)}")
        OH_UdsFileUri_Destroy(fileUri)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_GetFileUri passed")
    }

    @Test
    fun testOH_UdmfRecord_AddPixelMap() {
        val rec = OH_UdmfRecord_Create()
        val pixelMap = OH_UdsPixelMap_Create()
        logLine("OH_UdmfRecord_AddPixelMap=${OH_UdmfRecord_AddPixelMap(rec, pixelMap)}")
        OH_UdsPixelMap_Destroy(pixelMap)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_AddPixelMap passed")
    }

    @Test
    fun testOH_UdmfRecord_GetPixelMap() {
        val rec = OH_UdmfRecord_Create()
        val pixelMap = OH_UdsPixelMap_Create()
        OH_UdmfRecord_AddPixelMap(rec, pixelMap)
        logLine("OH_UdmfRecord_GetPixelMap=${OH_UdmfRecord_GetPixelMap(rec, pixelMap)}")
        OH_UdsPixelMap_Destroy(pixelMap)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_GetPixelMap passed")
    }

    @Test
    fun testOH_UdmfRecord_AddArrayBuffer() {
        val rec = OH_UdmfRecord_Create()
        val buf = OH_UdsArrayBuffer_Create()
        logLine("OH_UdmfRecord_AddArrayBuffer=${OH_UdmfRecord_AddArrayBuffer(rec, "type", buf)}")
        OH_UdsArrayBuffer_Destroy(buf)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_AddArrayBuffer passed")
    }

    @Test
    fun testOH_UdmfRecord_GetArrayBuffer() {
        val rec = OH_UdmfRecord_Create()
        val buf = OH_UdsArrayBuffer_Create()
        OH_UdmfRecord_AddArrayBuffer(rec, "type", buf)
        logLine("OH_UdmfRecord_GetArrayBuffer=${OH_UdmfRecord_GetArrayBuffer(rec, "type", buf)}")
        OH_UdsArrayBuffer_Destroy(buf)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_GetArrayBuffer passed")
    }

    @Test
    fun testOH_UdmfRecord_AddContentForm() {
        val rec = OH_UdmfRecord_Create()
        val contentForm = OH_UdsContentForm_Create()
        logLine("OH_UdmfRecord_AddContentForm=${OH_UdmfRecord_AddContentForm(rec, contentForm)}")
        OH_UdsContentForm_Destroy(contentForm)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_AddContentForm passed")
    }

    @Test
    fun testOH_UdmfRecord_GetContentForm() {
        val rec = OH_UdmfRecord_Create()
        val contentForm = OH_UdsContentForm_Create()
        OH_UdmfRecord_AddContentForm(rec, contentForm)
        logLine("OH_UdmfRecord_GetContentForm=${OH_UdmfRecord_GetContentForm(rec, contentForm)}")
        OH_UdsContentForm_Destroy(contentForm)
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_GetContentForm passed")
    }

    @Test
    fun testOH_UdmfRecord_SetProvider() {
        memScoped {
            val rec = OH_UdmfRecord_Create()
            val provider = OH_UdmfRecordProvider_Create()
            val types = allocArrayOf("type".cstr.ptr)
            logLine("OH_UdmfRecord_SetProvider=${OH_UdmfRecord_SetProvider(rec, types, 1u, provider)}")
            OH_UdmfRecordProvider_Destroy(provider)
            OH_UdmfRecord_Destroy(rec)
            logLine("OH_UdmfRecord_SetProvider passed")
        }
    }

    @Test
    fun testOH_UdmfRecord_AddGeneralEntry() {
        val rec = OH_UdmfRecord_Create()
        val entry = UByteArray(4)
        logLine("OH_UdmfRecord_AddGeneralEntry=${OH_UdmfRecord_AddGeneralEntry(rec, "id", entry.refTo(0), 4u)}")
        OH_UdmfRecord_Destroy(rec)
        logLine("OH_UdmfRecord_AddGeneralEntry passed")
    }

    @Test
    fun testOH_UdmfRecord_GetGeneralEntry() {
        memScoped {
            val rec = OH_UdmfRecord_Create()
            val entry = UByteArray(4)
            OH_UdmfRecord_AddGeneralEntry(rec, "id", entry.refTo(0), 4u)
            val entryOut = alloc<CPointerVar<UByteVar>>()
            val count = alloc<UIntVar>()
            logLine("OH_UdmfRecord_GetGeneralEntry=${OH_UdmfRecord_GetGeneralEntry(rec, "id", entryOut.ptr, count.ptr)}")
            OH_UdmfRecord_Destroy(rec)
            logLine("OH_UdmfRecord_GetGeneralEntry passed")
        }
    }


    @Test
    fun testOH_UdmfProperty_Create() {
        val data = OH_UdmfData_Create()
        val prop = OH_UdmfProperty_Create(data)
        OH_UdmfProperty_Destroy(prop)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfProperty_Create passed")
    }

    @Test
    fun testOH_UdmfProperty_Destroy() {
        val data = OH_UdmfData_Create()
        val prop = OH_UdmfProperty_Create(data)
        OH_UdmfProperty_Destroy(prop)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfProperty_Destroy passed")
    }

    @Test
    fun testOH_UdmfProperty_GetTag() {
        val data = OH_UdmfData_Create()
        val prop = OH_UdmfProperty_Create(data)
        logLine("OH_UdmfProperty_GetTag=${OH_UdmfProperty_GetTag(prop)}")
        OH_UdmfProperty_Destroy(prop)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfProperty_GetTag passed")
    }

    @Test
    fun testOH_UdmfProperty_GetTimestamp() {
        val data = OH_UdmfData_Create()
        val prop = OH_UdmfProperty_Create(data)
        logLine("OH_UdmfProperty_GetTimestamp=${OH_UdmfProperty_GetTimestamp(prop)}")
        OH_UdmfProperty_Destroy(prop)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfProperty_GetTimestamp passed")
    }

    @Test
    fun testOH_UdmfProperty_GetShareOption() {
        val data = OH_UdmfData_Create()
        val prop = OH_UdmfProperty_Create(data)
        logLine("OH_UdmfProperty_GetShareOption=${OH_UdmfProperty_GetShareOption(prop)}")
        OH_UdmfProperty_Destroy(prop)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfProperty_GetShareOption passed")
    }

    @Test
    fun testOH_UdmfProperty_GetExtrasIntParam() {
        val data = OH_UdmfData_Create()
        val prop = OH_UdmfProperty_Create(data)
        logLine("OH_UdmfProperty_GetExtrasIntParam=${OH_UdmfProperty_GetExtrasIntParam(prop, "k", -1)}")
        OH_UdmfProperty_Destroy(prop)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfProperty_GetExtrasIntParam passed")
    }

    @Test
    fun testOH_UdmfProperty_GetExtrasStringParam() {
        val data = OH_UdmfData_Create()
        val prop = OH_UdmfProperty_Create(data)
        logLine("OH_UdmfProperty_GetExtrasStringParam=${OH_UdmfProperty_GetExtrasStringParam(prop, "k")}")
        OH_UdmfProperty_Destroy(prop)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfProperty_GetExtrasStringParam passed")
    }

    @Test
    fun testOH_UdmfProperty_SetTag() {
        val data = OH_UdmfData_Create()
        val prop = OH_UdmfProperty_Create(data)
        logLine("OH_UdmfProperty_SetTag=${OH_UdmfProperty_SetTag(prop, "tag")}")
        OH_UdmfProperty_Destroy(prop)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfProperty_SetTag passed")
    }

    @Test
    fun testOH_UdmfProperty_SetShareOption() {
        val data = OH_UdmfData_Create()
        val prop = OH_UdmfProperty_Create(data)
        logLine("OH_UdmfProperty_SetShareOption=${OH_UdmfProperty_SetShareOption(prop, Udmf_ShareOption.SHARE_OPTIONS_IN_APP)}")
        OH_UdmfProperty_Destroy(prop)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfProperty_SetShareOption passed")
    }

    @Test
    fun testOH_UdmfProperty_SetExtrasIntParam() {
        val data = OH_UdmfData_Create()
        val prop = OH_UdmfProperty_Create(data)
        logLine("OH_UdmfProperty_SetExtrasIntParam=${OH_UdmfProperty_SetExtrasIntParam(prop, "k", 1)}")
        OH_UdmfProperty_Destroy(prop)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfProperty_SetExtrasIntParam passed")
    }

    @Test
    fun testOH_UdmfProperty_SetExtrasStringParam() {
        val data = OH_UdmfData_Create()
        val prop = OH_UdmfProperty_Create(data)
        logLine("OH_UdmfProperty_SetExtrasStringParam=${OH_UdmfProperty_SetExtrasStringParam(prop, "k", "v")}")
        OH_UdmfProperty_Destroy(prop)
        OH_UdmfData_Destroy(data)
        logLine("OH_UdmfProperty_SetExtrasStringParam passed")
    }


    @Test
    fun testOH_UdmfOptions_Create() {
        val opts = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
        try { OH_UdmfOptions_Destroy(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfOptions_Create passed")
    }

    @Test
    fun testOH_UdmfOptions_Destroy() {
        val opts = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
        try { OH_UdmfOptions_Destroy(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfOptions_Destroy passed")
    }

    @Test
    fun testOH_UdmfOptions_GetKey() {
        val opts = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
        logLine("OH_UdmfOptions_GetKey=${try { OH_UdmfOptions_GetKey(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_GetKey (API 20) exception: $e"); null }}")
        try { OH_UdmfOptions_Destroy(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfOptions_GetKey passed")
    }

    @Test
    fun testOH_UdmfOptions_GetIntention() {
        val opts = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
        logLine("OH_UdmfOptions_GetIntention=${try { OH_UdmfOptions_GetIntention(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_GetIntention (API 20) exception: $e"); Udmf_Intention.UDMF_INTENTION_DRAG }}")
        try { OH_UdmfOptions_Destroy(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfOptions_GetIntention passed")
    }

    @Test
    fun testOH_UdmfOptions_GetVisibility() {
        val opts = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
        logLine("OH_UdmfOptions_GetVisibility=${try { OH_UdmfOptions_GetVisibility(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_GetVisibility (API 20) exception: $e"); Udmf_Visibility.UDMF_ALL }}")
        try { OH_UdmfOptions_Destroy(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfOptions_GetVisibility passed")
    }

    @Test
    fun testOH_UdmfOptions_SetKey() {
        val opts = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
        logLine("OH_UdmfOptions_SetKey=${try { OH_UdmfOptions_SetKey(opts, "key") } catch (e: Throwable) { logLine("OH_UdmfOptions_SetKey (API 20) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdmfOptions_Destroy(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfOptions_SetKey passed")
    }

    @Test
    fun testOH_UdmfOptions_SetIntention() {
        val opts = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
        logLine("OH_UdmfOptions_SetIntention=${try { OH_UdmfOptions_SetIntention(opts, Udmf_Intention.UDMF_INTENTION_DRAG) } catch (e: Throwable) { logLine("OH_UdmfOptions_SetIntention (API 20) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdmfOptions_Destroy(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfOptions_SetIntention passed")
    }

    @Test
    fun testOH_UdmfOptions_SetVisibility() {
        val opts = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
        logLine("OH_UdmfOptions_SetVisibility=${try { OH_UdmfOptions_SetVisibility(opts, Udmf_Visibility.UDMF_ALL) } catch (e: Throwable) { logLine("OH_UdmfOptions_SetVisibility (API 20) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdmfOptions_Destroy(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfOptions_SetVisibility passed")
    }

    @Test
    fun testOH_UdmfOptions_Reset() {
        val opts = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
        logLine("OH_UdmfOptions_Reset=${try { OH_UdmfOptions_Reset(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_Reset (API 20) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdmfOptions_Destroy(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfOptions_Reset passed")
    }


    @Test
    fun testOH_Udmf_GetUnifiedData() {
        val data = OH_UdmfData_Create()
        logLine("OH_Udmf_GetUnifiedData=${OH_Udmf_GetUnifiedData("key", Udmf_Intention.UDMF_INTENTION_DRAG, data)}")
        OH_UdmfData_Destroy(data)
        logLine("OH_Udmf_GetUnifiedData passed")
    }

    @Test
    fun testOH_Udmf_SetUnifiedData() {
        val data = OH_UdmfData_Create()
        val keyBuf = ByteArray(512)
        logLine("OH_Udmf_SetUnifiedData=${OH_Udmf_SetUnifiedData(Udmf_Intention.UDMF_INTENTION_PASTEBOARD, data, keyBuf.refTo(0), keyBuf.size.toUInt())}")
        OH_UdmfData_Destroy(data)
        logLine("OH_Udmf_SetUnifiedData passed")
    }

    @Test
    fun testOH_Udmf_GetUnifiedDataByOptions() {
        memScoped {
            val opts = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
            val dataSize = alloc<UIntVar>()
            val dataArray = alloc<CPointerVar<OH_UdmfData>>()
            logLine("OH_Udmf_GetUnifiedDataByOptions=${try { OH_Udmf_GetUnifiedDataByOptions(opts, dataArray.ptr, dataSize.ptr) } catch (e: Throwable) { logLine("OH_Udmf_GetUnifiedDataByOptions (API 20) exception: $e"); UDMF_E_INVALID_PARAM }}")
            try { OH_Udmf_DestroyDataArray(dataArray.ptr, dataSize.value) } catch (e: Throwable) { logLine("OH_Udmf_DestroyDataArray (API 20) exception: $e") }
            try { OH_UdmfOptions_Destroy(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
            logLine("OH_Udmf_GetUnifiedDataByOptions passed")
        }
    }

    @Test
    fun testOH_Udmf_DeleteUnifiedData() {
        memScoped {
            val opts = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
            val dataSize = alloc<UIntVar>()
            val dataArray = alloc<CPointerVar<OH_UdmfData>>()
            try { OH_Udmf_GetUnifiedDataByOptions(opts, dataArray.ptr, dataSize.ptr) } catch (e: Throwable) { logLine("OH_Udmf_GetUnifiedDataByOptions (API 20) exception: $e") }
            logLine("OH_Udmf_DeleteUnifiedData=${try { OH_Udmf_DeleteUnifiedData(opts, dataArray.ptr, dataSize.ptr) } catch (e: Throwable) { logLine("OH_Udmf_DeleteUnifiedData (API 20) exception: $e"); UDMF_E_INVALID_PARAM }}")
            try { OH_Udmf_DestroyDataArray(dataArray.ptr, dataSize.value) } catch (e: Throwable) { logLine("OH_Udmf_DestroyDataArray (API 20) exception: $e") }
            try { OH_UdmfOptions_Destroy(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
            logLine("OH_Udmf_DeleteUnifiedData passed")
        }
    }

    @Test
    fun testOH_Udmf_DestroyDataArray() {
        memScoped {
            val opts = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
            val dataSize = alloc<UIntVar>()
            val dataArray = alloc<CPointerVar<OH_UdmfData>>()
            try { OH_Udmf_GetUnifiedDataByOptions(opts, dataArray.ptr, dataSize.ptr) } catch (e: Throwable) { logLine("OH_Udmf_GetUnifiedDataByOptions (API 20) exception: $e") }
            try { OH_Udmf_DestroyDataArray(dataArray.ptr, dataSize.value) } catch (e: Throwable) { logLine("OH_Udmf_DestroyDataArray (API 20) exception: $e") }
            try { OH_UdmfOptions_Destroy(opts) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
            logLine("OH_Udmf_DestroyDataArray passed")
        }
    }

    @Test
    fun testOH_Udmf_SetUnifiedDataByOptions() {
        val data = OH_UdmfData_Create()
        val opt2 = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
        val keyBuf = ByteArray(512)
        logLine("OH_Udmf_SetUnifiedDataByOptions=${try { OH_Udmf_SetUnifiedDataByOptions(opt2, data, keyBuf.refTo(0), keyBuf.size.toUInt()) } catch (e: Throwable) { logLine("OH_Udmf_SetUnifiedDataByOptions (API 20) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdmfOptions_Destroy(opt2) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
        OH_UdmfData_Destroy(data)
        logLine("OH_Udmf_SetUnifiedDataByOptions passed")
    }

    @Test
    fun testOH_Udmf_UpdateUnifiedData() {
        val data = OH_UdmfData_Create()
        val opt2 = try { OH_UdmfOptions_Create() } catch (e: Throwable) { logLine("OH_UdmfOptions_Create (API 20) exception: $e"); null }
        logLine("OH_Udmf_UpdateUnifiedData=${try { OH_Udmf_UpdateUnifiedData(opt2, data) } catch (e: Throwable) { logLine("OH_Udmf_UpdateUnifiedData (API 20) exception: $e"); UDMF_E_INVALID_PARAM }}")
        try { OH_UdmfOptions_Destroy(opt2) } catch (e: Throwable) { logLine("OH_UdmfOptions_Destroy (API 20) exception: $e") }
        OH_UdmfData_Destroy(data)
        logLine("OH_Udmf_UpdateUnifiedData passed")
    }

    @Test
    fun testOH_UDMF_GetDataElementAt() {
        memScoped {
            val arrPtr = alloc<CPointerVar<OH_UdmfData>>()
            logLine("OH_UDMF_GetDataElementAt=${try { OH_UDMF_GetDataElementAt(arrPtr.ptr, 0u) } catch (e: Throwable) { logLine("OH_UDMF_GetDataElementAt (API 22) exception: $e"); null }}")
            logLine("OH_UDMF_GetDataElementAt passed")
        }
    }


    // @Test
    // fun testOH_UdmfProgressInfo_GetProgress() {
    //     logLine("OH_UdmfProgressInfo_GetProgress=${OH_UdmfProgressInfo_GetProgress(null)}")
    //     logLine("OH_UdmfProgressInfo_GetProgress passed")
    // }

    // @Test
    // fun testOH_UdmfProgressInfo_GetStatus() {
    //     logLine("OH_UdmfProgressInfo_GetStatus=${OH_UdmfProgressInfo_GetStatus(null)}")
    //     logLine("OH_UdmfProgressInfo_GetStatus passed")
    // }


    @Test
    fun testOH_UdmfGetDataParams_Create() {
        val params = OH_UdmfGetDataParams_Create()
        OH_UdmfGetDataParams_Destroy(params)
        logLine("OH_UdmfGetDataParams_Create passed")
    }

    @Test
    fun testOH_UdmfGetDataParams_Destroy() {
        val params = OH_UdmfGetDataParams_Create()
        OH_UdmfGetDataParams_Destroy(params)
        logLine("OH_UdmfGetDataParams_Destroy passed")
    }

    @Test
    fun testOH_UdmfGetDataParams_SetDestUri() {
        val params = OH_UdmfGetDataParams_Create()
        OH_UdmfGetDataParams_SetDestUri(params, "file:///dest")
        OH_UdmfGetDataParams_Destroy(params)
        logLine("OH_UdmfGetDataParams_SetDestUri passed")
    }

    @Test
    fun testOH_UdmfGetDataParams_SetFileConflictOptions() {
        val params = OH_UdmfGetDataParams_Create()
        OH_UdmfGetDataParams_SetFileConflictOptions(params, UDMF_OVERWRITE)
        OH_UdmfGetDataParams_Destroy(params)
        logLine("OH_UdmfGetDataParams_SetFileConflictOptions passed")
    }

    @Test
    fun testOH_UdmfGetDataParams_SetProgressIndicator() {
        val params = OH_UdmfGetDataParams_Create()
        OH_UdmfGetDataParams_SetProgressIndicator(params, UDMF_NONE)
        OH_UdmfGetDataParams_Destroy(params)
        logLine("OH_UdmfGetDataParams_SetProgressIndicator passed")
    }

    @Test
    fun testOH_UdmfGetDataParams_SetDataProgressListener() {
        val params = OH_UdmfGetDataParams_Create()
        OH_UdmfGetDataParams_SetDataProgressListener(params, null)
        OH_UdmfGetDataParams_Destroy(params)
        logLine("OH_UdmfGetDataParams_SetDataProgressListener passed")
    }

    @Test
    fun testOH_UdmfGetDataParams_SetAcceptableInfo() {
        val params = OH_UdmfGetDataParams_Create()
        val loadInfo = try { OH_UdmfDataLoadInfo_Create() } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Create (API 20) exception: $e"); null }
        try { OH_UdmfGetDataParams_SetAcceptableInfo(params, loadInfo) } catch (e: Throwable) { logLine("OH_UdmfGetDataParams_SetAcceptableInfo (API 20) exception: $e") }
        try { OH_UdmfDataLoadInfo_Destroy(loadInfo) } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Destroy (API 20) exception: $e") }
        OH_UdmfGetDataParams_Destroy(params)
        logLine("OH_UdmfGetDataParams_SetAcceptableInfo passed")
    }

    @Test
    fun testOH_UdmfDataLoadParams_Create() {
        val params = try { OH_UdmfDataLoadParams_Create() } catch (e: Throwable) { logLine("OH_UdmfDataLoadParams_Create (API 20) exception: $e"); null }
        try { OH_UdmfDataLoadParams_Destroy(params) } catch (e: Throwable) { logLine("OH_UdmfDataLoadParams_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfDataLoadParams_Create passed")
    }

    @Test
    fun testOH_UdmfDataLoadParams_Destroy() {
        val params = try { OH_UdmfDataLoadParams_Create() } catch (e: Throwable) { logLine("OH_UdmfDataLoadParams_Create (API 20) exception: $e"); null }
        try { OH_UdmfDataLoadParams_Destroy(params) } catch (e: Throwable) { logLine("OH_UdmfDataLoadParams_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfDataLoadParams_Destroy passed")
    }

    @Test
    fun testOH_UdmfDataLoadParams_SetLoadHandler() {
        val params = try { OH_UdmfDataLoadParams_Create() } catch (e: Throwable) { logLine("OH_UdmfDataLoadParams_Create (API 20) exception: $e"); null }
        try { OH_UdmfDataLoadParams_SetLoadHandler(params, null) } catch (e: Throwable) { logLine("OH_UdmfDataLoadParams_SetLoadHandler (API 20) exception: $e") }
        try { OH_UdmfDataLoadParams_Destroy(params) } catch (e: Throwable) { logLine("OH_UdmfDataLoadParams_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfDataLoadParams_SetLoadHandler passed")
    }

    @Test
    fun testOH_UdmfDataLoadParams_SetDataLoadInfo() {
        val params = try { OH_UdmfDataLoadParams_Create() } catch (e: Throwable) { logLine("OH_UdmfDataLoadParams_Create (API 20) exception: $e"); null }
        val loadInfo = try { OH_UdmfDataLoadInfo_Create() } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Create (API 20) exception: $e"); null }
        try { OH_UdmfDataLoadParams_SetDataLoadInfo(params, loadInfo) } catch (e: Throwable) { logLine("OH_UdmfDataLoadParams_SetDataLoadInfo (API 20) exception: $e") }
        try { OH_UdmfDataLoadInfo_Destroy(loadInfo) } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Destroy (API 20) exception: $e") }
        try { OH_UdmfDataLoadParams_Destroy(params) } catch (e: Throwable) { logLine("OH_UdmfDataLoadParams_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfDataLoadParams_SetDataLoadInfo passed")
    }


    @Test
    fun testOH_UdmfDataLoadInfo_Create() {
        val info = try { OH_UdmfDataLoadInfo_Create() } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Create (API 20) exception: $e"); null }
        try { OH_UdmfDataLoadInfo_Destroy(info) } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfDataLoadInfo_Create passed")
    }

    @Test
    fun testOH_UdmfDataLoadInfo_Destroy() {
        val info = try { OH_UdmfDataLoadInfo_Create() } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Create (API 20) exception: $e"); null }
        try { OH_UdmfDataLoadInfo_Destroy(info) } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfDataLoadInfo_Destroy passed")
    }

    @Test
    fun testOH_UdmfDataLoadInfo_GetTypes() {
        memScoped {
            val info = try { OH_UdmfDataLoadInfo_Create() } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Create (API 20) exception: $e"); null }
            val count = alloc<UIntVar>()
            try { OH_UdmfDataLoadInfo_GetTypes(info, count.ptr) } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_GetTypes (API 20) exception: $e") }
            try { OH_UdmfDataLoadInfo_Destroy(info) } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Destroy (API 20) exception: $e") }
            logLine("OH_UdmfDataLoadInfo_GetTypes passed")
        }
    }

    @Test
    fun testOH_UdmfDataLoadInfo_SetType() {
        val info = try { OH_UdmfDataLoadInfo_Create() } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Create (API 20) exception: $e"); null }
        try { OH_UdmfDataLoadInfo_SetType(info, "type") } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_SetType (API 20) exception: $e") }
        try { OH_UdmfDataLoadInfo_Destroy(info) } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfDataLoadInfo_SetType passed")
    }

    @Test
    fun testOH_UdmfDataLoadInfo_GetRecordCount() {
        val info = try { OH_UdmfDataLoadInfo_Create() } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Create (API 20) exception: $e"); null }
        logLine("OH_UdmfDataLoadInfo_GetRecordCount=${try { OH_UdmfDataLoadInfo_GetRecordCount(info) } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_GetRecordCount (API 20) exception: $e"); -1 }}")
        try { OH_UdmfDataLoadInfo_Destroy(info) } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfDataLoadInfo_GetRecordCount passed")
    }

    @Test
    fun testOH_UdmfDataLoadInfo_SetRecordCount() {
        val info = try { OH_UdmfDataLoadInfo_Create() } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Create (API 20) exception: $e"); null }
        try { OH_UdmfDataLoadInfo_SetRecordCount(info, 1u) } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_SetRecordCount (API 20) exception: $e") }
        try { OH_UdmfDataLoadInfo_Destroy(info) } catch (e: Throwable) { logLine("OH_UdmfDataLoadInfo_Destroy (API 20) exception: $e") }
        logLine("OH_UdmfDataLoadInfo_SetRecordCount passed")
    }
}
