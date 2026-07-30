// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 14 -> sentence 14
 *                declarations, declarations-with-type-parameters -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: unbound property reference on generic class Box<Int>::v resolved as (Box<Int>) -> Int via specified type argument, verifying runtime semantics
 */

class Box<T>(val v: T)

// TESTCASE NUMBER: 1
fun test(b: Box<Int>): Int {
    val read: (Box<Int>) -> Int = Box<Int>::v
    return read(b)
}

fun box(): String {
    if (test(Box(1)) != 1) return "NOK"
    if (test(Box(42)) != 42) return "NOK"
    if (test(Box(-1)) != -1) return "NOK"
    return "OK"
}
