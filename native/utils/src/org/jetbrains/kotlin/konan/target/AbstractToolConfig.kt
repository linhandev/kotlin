/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

 package org.jetbrains.kotlin.konan.target

 import org.jetbrains.kotlin.konan.util.defaultTargetSubstitutions
 
 abstract class AbstractToolConfig(konanHome: String, userProvidedTargetName: String?, propertyOverrides: Map<String, String>, konanDataDir: String? = null) {
     private val distribution = Distribution(konanHome, propertyOverrides = propertyOverrides, konanDataDir = konanDataDir)
     private val platformManager = PlatformManager(distribution)
     private val targetManager = platformManager.targetManager(userProvidedTargetName)
     private val host = HostManager.host
     val target = targetManager.target
 
     protected val platform = platformManager.platform(target)
 
     val substitutions = defaultTargetSubstitutions(target)
 
     fun downloadDependencies() = platform.downloadDependencies()
 
     val llvmHome = platform.absoluteLlvmHome
     val sysRoot = platform.absoluteTargetSysRoot
 
     /**
      * Ordered sysroot roots for cinterop header ids in klib manifests. OHOS may use a second unpacked sysroot
      * ([OhosConfigurables.additionalTargetSysRoot]); headers there must be relativized like the primary sysroot.
      */
     fun headerIdRootPaths(): List<String> = buildList {
         add(sysRoot)
         val configurables = platform.configurables
         if (configurables is OhosConfigurables) {
             configurables.additionalTargetSysRoot?.let { name ->
                 add(configurables.absolute(name))
             }
         }
     }
 
     val libclang = when (host) {
         KonanTarget.MINGW_X64 -> "$llvmHome/bin/libclang.dll"
         else -> "$llvmHome/lib/${System.mapLibraryName("clang")}"
     }
 
     abstract fun loadLibclang()
 
     fun prepare() {
         downloadDependencies()
 
         loadLibclang()
     }
 }