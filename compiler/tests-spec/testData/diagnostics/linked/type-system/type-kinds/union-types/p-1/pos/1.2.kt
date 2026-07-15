// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, union-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Union types in when and if expressions resolve to common supertype LUB
 * HELPERS: checkType
 */

open class Base
class Derived1 : Base()
class Derived2 : Base()

// TESTCASE NUMBER: 1
fun case_1(x: Any): Comparable<*> = when (x) {
    is Int -> 1
    is String -> "a"
    else -> 0
}


// TESTCASE NUMBER: 2
fun case_2() {
    val x = if (true) true else false
    checkSubtype<Boolean>(x)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val x = if (true) 1.toByte() else 2.toShort()
    checkSubtype<Number>(x)
}


// TESTCASE NUMBER: 4
fun case_4(flag: Boolean) {
    val x = if (flag) Derived1() else Derived2()
    checkSubtype<Base>(x)
}
