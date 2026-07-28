/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.compiler.plugins.kotlin.lower.hiddenfromc

import androidx.compose.compiler.plugins.kotlin.FeatureFlags
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer
import androidx.compose.compiler.plugins.kotlin.lower.AbstractComposeLowering
import androidx.compose.compiler.plugins.kotlin.lower.containsComposableAnnotation
import androidx.compose.compiler.plugins.kotlin.lower.hasFirDeclaration
import androidx.compose.compiler.plugins.kotlin.lower.needsComposableRemapping
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.fromSymbolOwner
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.constructors
import org.jetbrains.kotlin.ir.util.isLocal
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.platform.konan.isNative

val hiddenFromCClassId = ClassId.fromString("kotlin/native/HiddenFromC")

/**
 * Adds `kotlin.native.HiddenFromC` to Composable functions, properties, and related data classes.
 * K2: annotations are written via [addMetadataVisibleAnnotationsToElement] (FIR metadata).
 */
class AddHiddenFromCLowering(
    private val pluginContext: IrPluginContext,
    metrics: ModuleMetrics,
    stabilityInferencer: StabilityInferencer,
    featureFlags: FeatureFlags,
) : AbstractComposeLowering(
    pluginContext,
    metrics,
    stabilityInferencer,
    featureFlags
) {

    private val hiddenFromCAnnotation: IrClassSymbol by lazy {
        getTopLevelClass(hiddenFromCClassId)
    }

    private var currentShouldAnnotateClass = false

    override fun lower(irModule: IrModuleFragment) {
        require(context.platform.isNative()) {
            "AddHiddenFromCLowering is expected to run only for k/native. " +
                    "The platform - ${context.platform}"
        }
        irModule.transformChildrenVoid(this)
    }

    override fun visitClass(declaration: IrClass): IrStatement {
        val previousShouldAnnotateClass = currentShouldAnnotateClass
        currentShouldAnnotateClass = false

        val cls = super.visitClass(declaration) as IrClass

        if (currentShouldAnnotateClass && cls.isData) {
            cls.addHiddenFromCAnnotation()
        }

        currentShouldAnnotateClass = previousShouldAnnotateClass
        return cls
    }

    private fun IrFunction.isSyntheticFun(): Boolean =
        origin == IrDeclarationOrigin.FAKE_OVERRIDE || startOffset < 0 || endOffset < 0

    override fun visitFunction(declaration: IrFunction): IrStatement {
        val f = super.visitFunction(declaration) as IrFunction
        if (f.isLocal || f.isSyntheticFun() ||
            !(f.visibility == DescriptorVisibilities.PUBLIC ||
                    f.visibility == DescriptorVisibilities.PROTECTED)
        )
            return f

        if (f.hasComposableAnnotation() || f.needsComposableRemapping()) {
            f.addHiddenFromCAnnotation()
            currentShouldAnnotateClass = true
        }

        return f
    }

    override fun visitProperty(declaration: IrProperty): IrStatement {
        val p = super.visitProperty(declaration) as IrProperty
        if (p.isLocal || p.getter?.isSyntheticFun() == true || p.visibility != DescriptorVisibilities.PUBLIC) return p

        val shouldAdd = p.getter?.hasComposableAnnotation() ?: false ||
                p.getter?.needsComposableRemapping() ?: false ||
                p.backingField?.type.containsComposableAnnotation()

        if (shouldAdd) {
            p.addHiddenFromCAnnotation()
            currentShouldAnnotateClass = true
        }

        return p
    }

    private fun IrDeclaration.addHiddenFromCAnnotation() {
        if (!hasFirDeclaration()) {
            return
        }
        val annotation = IrConstructorCallImpl.fromSymbolOwner(
            type = hiddenFromCAnnotation.defaultType,
            constructorSymbol = hiddenFromCAnnotation.constructors.first()
        )
        pluginContext.metadataDeclarationRegistrar.addMetadataVisibleAnnotationsToElement(this, annotation)
    }
}
