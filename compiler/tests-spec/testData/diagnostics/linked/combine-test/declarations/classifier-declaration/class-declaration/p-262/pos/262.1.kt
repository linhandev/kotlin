// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 262 -> sentence 262
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 262 -> sentence 262
 *                inheritance, inheriting -> paragraph 262 -> sentence 262
 * NUMBER: 1
 * DESCRIPTION: precise types when an internal class is used within the same module without exposing it from public API signatures
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
internal class ModuleOnly(val id: Int = 1)

internal fun makeModuleOnly(): ModuleOnly = ModuleOnly(1)

fun moduleOnlyId(): Int = ModuleOnly(1).id

fun case1() {
    val m = makeModuleOnly()
    m checkType { check<ModuleOnly>() }
    checkSubtype<ModuleOnly>(m)
    m.id checkType { check<Int>() }
    moduleOnlyId() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
internal open class ModuleBase(val label: String)

internal class ModuleChild(label: String, val extra: Int) : ModuleBase(label)

internal fun makeChild(): ModuleChild = ModuleChild("m", 2)

fun case2() {
    val c = makeChild()
    c checkType { check<ModuleChild>() }
    checkSubtype<ModuleBase>(c)
    c.label checkType { check<String>() }
    c.extra checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
internal class ModuleBox<T>(val value: T)

internal fun makeBox(): ModuleBox<String> = ModuleBox("ok")

fun case3() {
    val b = makeBox()
    b checkType { check<ModuleBox<String>>() }
    b.value checkType { check<String>() }
}
