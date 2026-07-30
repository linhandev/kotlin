/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan.cexport

import org.jetbrains.kotlin.backend.konan.KonanFqNames
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor
import org.jetbrains.kotlin.resolve.descriptorUtil.annotationClass

/**
 * C adapter visibility for [@HiddenFromC] / [@HidesFromC].
 *
 * Descriptor rules mirror ObjC export hidden checks in [org.jetbrains.kotlin.backend.konan.objcexport].
 */
private fun AnnotationDescriptor.hidesFromC(): Boolean =
        annotationClass?.annotations?.any { it.fqName == KonanFqNames.hidesFromC } ?: false

internal fun CallableMemberDescriptor.isHiddenFromC(): Boolean = when {
    overriddenDescriptors.isNotEmpty() -> overriddenDescriptors.first().isHiddenFromC()
    contextReceiverParameters.isNotEmpty() -> false
    else -> annotations.any { it.hidesFromC() }
}

/**
 * Check if the given class or its enclosing declaration is marked as @HiddenFromC.
 */
internal fun ClassDescriptor.isHiddenFromC(): Boolean = when {
    containingDeclaration.let { it as? ClassDescriptor }?.isHiddenFromC() == true -> true
    annotations.any { it.hidesFromC() } -> true
    else -> false
}
