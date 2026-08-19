// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 262 -> sentence 262
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 262 -> sentence 262
 *                inheritance, inheriting -> paragraph 262 -> sentence 262
 * NUMBER: 1
 * DESCRIPTION: an internal class is usable in the same module (via internal API or by reading members from public functions); contrasts with p-260/p-261 public exposure and with declaration-visibility p-4 multi-file internal access
 */

// TESTCASE NUMBER: 1
internal class ModuleOnly(val id: Int = 1)

internal fun makeModuleOnly(): ModuleOnly = ModuleOnly(1)

fun moduleOnlyId(): Int = ModuleOnly(1).id

// TESTCASE NUMBER: 2
internal open class ModuleBase(val label: String)

internal class ModuleChild(label: String, val extra: Int) : ModuleBase(label)

internal fun makeChild(): ModuleChild = ModuleChild("m", 2)

fun childExtra(): Int = ModuleChild("m", 2).extra

// TESTCASE NUMBER: 3
internal class ModuleBox<T>(val value: T)

internal fun makeBox(): ModuleBox<String> = ModuleBox("ok")

fun boxValue(): String = ModuleBox("ok").value

fun box(): String {
    if (makeModuleOnly().id != 1) return "NOK: module-only"
    if (moduleOnlyId() != 1) return "NOK: module-only-id"
    val m: ModuleOnly = makeModuleOnly()
    if (m.id != 1) return "NOK: via-module-only"

    if (makeChild().label != "m") return "NOK: child-label"
    if (childExtra() != 2) return "NOK: child-extra"
    val asBase: ModuleBase = makeChild()
    if (asBase.label != "m") return "NOK: via-base"

    if (makeBox().value != "ok") return "NOK: box"
    if (boxValue() != "ok") return "NOK: box-value"
    val asBox: ModuleBox<String> = makeBox()
    if (asBox.value != "ok") return "NOK: via-box"
    return "OK"
}
