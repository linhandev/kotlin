// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 16 -> sentence 16
 *                declarations, classifier-declaration, companion-object -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: companion object class literal differs from outer class class literal, verifying runtime semantics
 */

class C { companion object }

// TESTCASE NUMBER: 1
fun test(): Boolean = C::class != C.Companion::class

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
