// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 29 -> sentence 29
 *                expressions, call-expressions -> paragraph 29 -> sentence 29
 *                type-system, introduction-1 -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: 调用顶层函数时显式 null 覆盖可空默认参数（声明+调用组合） type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun f(x: String? = "d"): String? = x

// TESTCASE NUMBER: 1
fun test(): String? = f(null)

fun case1() {
    checkSubtype<String?>(test())
}
