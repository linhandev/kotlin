// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: override of interface default is dynamically dispatched through interface-typed parameter with override as default argument
 */

// TESTCASE NUMBER: 1
interface DispatchI {
    fun f(): Int = 1
}

class UseDefault : DispatchI

class OverrideDefault : DispatchI {
    override fun f(): Int = 2
}

fun test(i: DispatchI = OverrideDefault()): Int = i.f()

fun box(): String {
    // default argument is OverrideDefault → dynamic dispatch yields 2
    if (test() != 2) return "NOK: default-arg-override"
    if (test(OverrideDefault()) != 2) return "NOK: explicit-override"
    if (test(UseDefault()) != 1) return "NOK: explicit-default-impl"
    val refs: List<DispatchI> = listOf(UseDefault(), OverrideDefault())
    if (refs.map { it.f() } != listOf(1, 2)) return "NOK: list-dispatch"
    return "OK"
}
