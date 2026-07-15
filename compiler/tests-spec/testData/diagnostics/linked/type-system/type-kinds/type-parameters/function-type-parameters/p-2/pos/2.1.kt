// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, function-type-parameters -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Function type parameters are well-formed concrete types in their declaring function context
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> case_1(x: T): T = x


// TESTCASE NUMBER: 2
fun <T, R> case_2(list: List<T>, f: (T) -> R): List<R> = list.map(f)


// TESTCASE NUMBER: 3
class Case3 {
    fun <T> identity(value: T): T = value
}


// TESTCASE NUMBER: 4
fun <T : Number> case_4(a: T, b: T): T = a


// TESTCASE NUMBER: 5
fun <T> List<T>.case_5(): T = first()
