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
class DeviceInfoTest {

    private fun logLine(message: String) {
        println("[stdout] DeviceInfoTest $message")
    }

    @Test
    fun testOH_GetDeviceType() {
        logLine("--- OH_GetDeviceType ---")
        try {
            val deviceType = platform.BasicServicesKit.DeviceInfo.OH_GetDeviceType()
            assertNotNull(deviceType)
            logLine("OH_GetDeviceType() result: ${deviceType?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetDeviceType exception: $e")
        }
    }

    @Test
    fun testOH_GetManufacture() {
        logLine("--- OH_GetManufacture ---")
        try {
            val manufacture = platform.BasicServicesKit.DeviceInfo.OH_GetManufacture()
            assertNotNull(manufacture)
            logLine("OH_GetManufacture() result: ${manufacture?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetManufacture exception: $e")
        }
    }

    @Test
    fun testOH_GetBrand() {
        logLine("--- OH_GetBrand ---")
        try {
            val brand = platform.BasicServicesKit.DeviceInfo.OH_GetBrand()
            assertNotNull(brand)
            logLine("OH_GetBrand() result: ${brand?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetBrand exception: $e")
        }
    }

    @Test
    fun testOH_GetMarketName() {
        logLine("--- OH_GetMarketName ---")
        try {
            val marketName = platform.BasicServicesKit.DeviceInfo.OH_GetMarketName()
            assertNotNull(marketName)
            logLine("OH_GetMarketName() result: ${marketName?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetMarketName exception: $e")
        }
    }

    @Test
    fun testOH_GetProductSeries() {
        logLine("--- OH_GetProductSeries ---")
        try {
            val productSeries = platform.BasicServicesKit.DeviceInfo.OH_GetProductSeries()
            assertNotNull(productSeries)
            logLine("OH_GetProductSeries() result: ${productSeries?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetProductSeries exception: $e")
        }
    }

    @Test
    fun testOH_GetProductModel() {
        logLine("--- OH_GetProductModel ---")
        try {
            val productModel = platform.BasicServicesKit.DeviceInfo.OH_GetProductModel()
            assertNotNull(productModel)
            logLine("OH_GetProductModel() result: ${productModel?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetProductModel exception: $e")
        }
    }

    @Test
    fun testOH_GetSoftwareModel() {
        logLine("--- OH_GetSoftwareModel ---")
        try {
            val softwareModel = platform.BasicServicesKit.DeviceInfo.OH_GetSoftwareModel()
            logLine("OH_GetSoftwareModel() result: ${softwareModel?.toKString() ?: "null"}")
            assertNotNull(softwareModel)
        } catch (e: Throwable) {
            logLine("OH_GetSoftwareModel exception: $e")
        }
    }

    @Test
    fun testOH_GetHardwareModel() {
        logLine("--- OH_GetHardwareModel ---")
        try {
            val hardwareModel = platform.BasicServicesKit.DeviceInfo.OH_GetHardwareModel()
            assertNotNull(hardwareModel)
            logLine("OH_GetHardwareModel() result: ${hardwareModel?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetHardwareModel exception: $e")
        }
    }

    @Test
    fun testOH_GetBootloaderVersion() {
        logLine("--- OH_GetBootloaderVersion ---")
        try {
            val bootloaderVersion = platform.BasicServicesKit.DeviceInfo.OH_GetBootloaderVersion()
            assertNotNull(bootloaderVersion)
            logLine("OH_GetBootloaderVersion() result: ${bootloaderVersion?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetBootloaderVersion exception: $e")
        }
    }

    @Test
    fun testOH_GetAbiList() {
        logLine("--- OH_GetAbiList ---")
        try {
            val abiList = platform.BasicServicesKit.DeviceInfo.OH_GetAbiList()
            assertNotNull(abiList)
            logLine("OH_GetAbiList() result: ${abiList?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetAbiList exception: $e")
        }
    }

    @Test
    fun testOH_GetSecurityPatchTag() {
        logLine("--- OH_GetSecurityPatchTag ---")
        try {
            val securityPatchTag = platform.BasicServicesKit.DeviceInfo.OH_GetSecurityPatchTag()
            assertNotNull(securityPatchTag)
            logLine("OH_GetSecurityPatchTag() result: ${securityPatchTag?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetSecurityPatchTag exception: $e")
        }
    }

    @Test
    fun testOH_GetDisplayVersion() {
        logLine("--- OH_GetDisplayVersion ---")
        try {
            val displayVersion = platform.BasicServicesKit.DeviceInfo.OH_GetDisplayVersion()
            assertNotNull(displayVersion)
            logLine("OH_GetDisplayVersion() result: ${displayVersion?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetDisplayVersion exception: $e")
        }
    }

    @Test
    fun testOH_GetIncrementalVersion() {
        logLine("--- OH_GetIncrementalVersion ---")
        try {
            val incrementalVersion = platform.BasicServicesKit.DeviceInfo.OH_GetIncrementalVersion()
            assertNotNull(incrementalVersion)
            logLine("OH_GetIncrementalVersion() result: ${incrementalVersion?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetIncrementalVersion exception: $e")
        }
    }

    @Test
    fun testOH_GetOsReleaseType() {
        logLine("--- OH_GetOsReleaseType ---")
        try {
            val osReleaseType = platform.BasicServicesKit.DeviceInfo.OH_GetOsReleaseType()
            assertNotNull(osReleaseType)
            logLine("OH_GetOsReleaseType() result: ${osReleaseType?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetOsReleaseType exception: $e")
        }
    }

    @Test
    fun testOH_GetOSFullName() {
        logLine("--- OH_GetOSFullName ---")
        try {
            val osFullName = platform.BasicServicesKit.DeviceInfo.OH_GetOSFullName()
            assertNotNull(osFullName)
            logLine("OH_GetOSFullName() result: ${osFullName?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetOSFullName exception: $e")
        }
    }

    @Test
    fun testOH_GetSdkApiVersion() {
        logLine("--- OH_GetSdkApiVersion ---")
        try {
            val sdkApiVersion = platform.BasicServicesKit.DeviceInfo.OH_GetSdkApiVersion()
            assertNotNull(sdkApiVersion)
            logLine("OH_GetSdkApiVersion() result: $sdkApiVersion")
        } catch (e: Throwable) {
            logLine("OH_GetSdkApiVersion exception: $e")
        }
    }

    @Test
    fun testOH_GetFirstApiVersion() {
        logLine("--- OH_GetFirstApiVersion ---")
        try {
            val firstApiVersion = platform.BasicServicesKit.DeviceInfo.OH_GetFirstApiVersion()
            assertNotNull(firstApiVersion)
            logLine("OH_GetFirstApiVersion() result: $firstApiVersion")
        } catch (e: Throwable) {
            logLine("OH_GetFirstApiVersion exception: $e")
        }
    }

    @Test
    fun testOH_GetVersionId() {
        logLine("--- OH_GetVersionId ---")
        try {
            val versionId = platform.BasicServicesKit.DeviceInfo.OH_GetVersionId()
            assertNotNull(versionId)
            logLine("OH_GetVersionId() result: ${versionId?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetVersionId exception: $e")
        }
    }

    @Test
    fun testOH_GetBuildType() {
        logLine("--- OH_GetBuildType ---")
        try {
            val buildType = platform.BasicServicesKit.DeviceInfo.OH_GetBuildType()
            assertNotNull(buildType)
            logLine("OH_GetBuildType() result: ${buildType?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetBuildType exception: $e")
        }
    }

    @Test
    fun testOH_GetBuildUser() {
        logLine("--- OH_GetBuildUser ---")
        try {
            val buildUser = platform.BasicServicesKit.DeviceInfo.OH_GetBuildUser()
            assertNotNull(buildUser)
            logLine("OH_GetBuildUser() result: ${buildUser?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetBuildUser exception: $e")
        }
    }

    @Test
    fun testOH_GetBuildHost() {
        logLine("--- OH_GetBuildHost ---")
        try {
            val buildHost = platform.BasicServicesKit.DeviceInfo.OH_GetBuildHost()
            assertNotNull(buildHost)
            logLine("OH_GetBuildHost() result: ${buildHost?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetBuildHost exception: $e")
        }
    }

    @Test
    fun testOH_GetBuildTime() {
        logLine("--- OH_GetBuildTime ---")
        try {
            val buildTime = platform.BasicServicesKit.DeviceInfo.OH_GetBuildTime()
            assertNotNull(buildTime)
            logLine("OH_GetBuildTime() result: ${buildTime?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetBuildTime exception: $e")
        }
    }

    @Test
    fun testOH_GetBuildRootHash() {
        logLine("--- OH_GetBuildRootHash ---")
        try {
            val buildRootHash = platform.BasicServicesKit.DeviceInfo.OH_GetBuildRootHash()
            assertNotNull(buildRootHash)
            logLine("OH_GetBuildRootHash() result: ${buildRootHash?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetBuildRootHash exception: $e")
        }
    }

    @Test
    fun testOH_GetDistributionOSName() {
        logLine("--- OH_GetDistributionOSName ---")
        try {
            val distributionOSName = platform.BasicServicesKit.DeviceInfo.OH_GetDistributionOSName()
            assertNotNull(distributionOSName)
            logLine("OH_GetDistributionOSName() result: ${distributionOSName?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetDistributionOSName exception: $e")
        }
    }

    @Test
    fun testOH_GetDistributionOSVersion() {
        logLine("--- OH_GetDistributionOSVersion ---")
        try {
            val distributionOSVersion = platform.BasicServicesKit.DeviceInfo.OH_GetDistributionOSVersion()
            assertNotNull(distributionOSVersion)
            logLine("OH_GetDistributionOSVersion() result: ${distributionOSVersion?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetDistributionOSVersion exception: $e")
        }
    }

    @Test
    fun testOH_GetDistributionOSApiVersion() {
        logLine("--- OH_GetDistributionOSApiVersion ---")
        try {
            val distributionOSApiVersion = platform.BasicServicesKit.DeviceInfo.OH_GetDistributionOSApiVersion()
            assertNotNull(distributionOSApiVersion)
            logLine("OH_GetDistributionOSApiVersion() result: $distributionOSApiVersion")
        } catch (e: Throwable) {
            logLine("OH_GetDistributionOSApiVersion exception: $e")
        }
    }

    @Test
    fun testOH_GetDistributionOSReleaseType() {
        logLine("--- OH_GetDistributionOSReleaseType ---")
        try {
            val distributionOSReleaseType = platform.BasicServicesKit.DeviceInfo.OH_GetDistributionOSReleaseType()
            assertNotNull(distributionOSReleaseType)
            logLine("OH_GetDistributionOSReleaseType() result: ${distributionOSReleaseType?.toKString() ?: "null"}")
        } catch (e: Throwable) {
            logLine("OH_GetDistributionOSReleaseType exception: $e")
        }
    }
}
