// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 17 -> sentence 17
 *                type-system, introduction-1 -> paragraph 17 -> sentence 17
 *                expressions, when-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable sealed class subject and else branch covering null and remaining subclasses type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Result {
    class Ok(val value: String) : Result()
    object Err : Result()
}

fun case1() {
    val r: Result? = Result.Ok("hello")
    checkSubtype<Int>(when (r) {
        is Result.Ok -> r.value.length
        else -> -1
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val r: Result? = Result.Err
    checkSubtype<Int>(when (r) {
        is Result.Ok -> r.value.length
        else -> -1
    })
}

// TESTCASE NUMBER: 3
fun case3() {
    val r: Result? = null
    checkSubtype<Int>(when (r) {
        is Result.Ok -> r.value.length
        else -> -1
    })
}
