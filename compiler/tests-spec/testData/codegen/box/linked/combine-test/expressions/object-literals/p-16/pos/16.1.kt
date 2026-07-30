
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: object declaration singleton differs from object literal new instances
 */

// TESTCASE NUMBER: 1
interface Marker

object Singleton : Marker

fun test(): Boolean {
    val a: Any = object : Marker {}
    val b: Any = object : Marker {}
    return Singleton === Singleton && a !== b && a !== Singleton
}

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
