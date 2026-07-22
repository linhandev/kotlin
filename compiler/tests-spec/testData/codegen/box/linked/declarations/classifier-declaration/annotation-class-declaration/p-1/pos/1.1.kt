// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, annotation-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: annotation class can be instantiated at runtime
 */

// TESTCASE NUMBER: 1
annotation class Ann(val x: Int)

fun box(): String {
    val a = Ann(42)
    return if (a is Annotation && a.x == 42) "OK" else "NOK"
}
