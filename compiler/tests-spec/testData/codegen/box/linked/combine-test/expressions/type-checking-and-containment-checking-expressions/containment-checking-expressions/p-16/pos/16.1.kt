// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, type-kinds, type-parameters -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: in operator resolves to extension contains on contravariant Box receiver with in-position type parameter at runtime
 */

// TESTCASE NUMBER: 1
class Box<in T>

operator fun <T> Box<in T>.contains(x: T): Boolean = true

fun test(): Boolean = 5 in Box<Int>()

fun box(): String {
    if (!test()) return "NOK"
    if (!(5 in Box<Int>())) return "NOK"
    return "OK"
}
