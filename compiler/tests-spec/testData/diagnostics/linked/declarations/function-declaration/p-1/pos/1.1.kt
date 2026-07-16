// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: top-level, member, local, and extension function declarations compile successfully
 */

// TESTCASE NUMBER: 1
fun topLevelFun(): Int = 1

// TESTCASE NUMBER: 2
class Holder {
    fun memberFun(): String = "member"
}

// TESTCASE NUMBER: 3
fun withLocalFun(): Int {
    fun localFun(x: Int) = x + 1
    return localFun(41)
}

// TESTCASE NUMBER: 4
fun <T> List<T>.lastOrNullSafe(): T? = if (isEmpty()) null else last()
