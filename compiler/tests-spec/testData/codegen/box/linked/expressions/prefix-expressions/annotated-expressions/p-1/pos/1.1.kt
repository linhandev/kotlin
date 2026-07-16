// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, prefix-expressions, annotated-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: annotations on operand do not change its evaluated value
 */

// TESTCASE NUMBER: 1

@Target(AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class Ann1

@Target(AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class Ann2

fun sum(a: Int, b: Int): Int = a + b

fun box(): String {
    val literal = @Ann1 @Ann2 42
    if (literal != 42) return "NOK"
    val expr = @Ann1 @Ann2 sum(1, 2)
    if (expr != 3) return "NOK"
    return "OK"
}
