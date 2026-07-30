// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: nested class class literal Outer.Inner::class uses fully qualified name, verifying runtime semantics
 */

class Outer { class Inner }

// TESTCASE NUMBER: 1
fun test(): kotlin.reflect.KClass<Outer.Inner> = Outer.Inner::class

fun box(): String {
    if (test() != Outer.Inner::class) return "NOK"
    return "OK"
}
