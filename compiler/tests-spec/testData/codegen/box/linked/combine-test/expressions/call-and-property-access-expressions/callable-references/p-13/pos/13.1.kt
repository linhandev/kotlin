// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 13 -> sentence 13
 *                declarations, classifier-declaration, companion-object -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: companion object member reference C.Companion::zero infers () -> Int and is invocable, verifying runtime semantics
 */

class C {
    companion object {
        fun zero(): Int = 0
    }
}

// TESTCASE NUMBER: 1
fun test(): Int {
    val f: () -> Int = C.Companion::zero
    return f()
}

fun box(): String {
    if (test() != 0) return "NOK"
    val g: () -> Int = C.Companion::zero
    if (g() != 0) return "NOK"
    return "OK"
}
