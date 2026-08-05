// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 25 -> sentence 25
 *                built-in-types-and-their-semantics, iterator-types -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: iterator() 返回类型缺少 hasNext/next 时失败
 */

// TESTCASE NUMBER: 1
class Bad { operator fun iterator(): Int = 0 }

fun test() { for (x in <!HAS_NEXT_MISSING, NEXT_MISSING!>Bad()<!>) { } }
