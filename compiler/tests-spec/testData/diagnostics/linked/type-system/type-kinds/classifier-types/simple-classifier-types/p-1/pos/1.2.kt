// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, classifier-types, simple-classifier-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, subtyping, subtyping-rules -> paragraph 2 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: Sealed, enum, object, and multi-supertype classifier types are well-formed
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Sealed6

class Sub6 : Sealed6()

fun case_1(x: Sub6) {
    checkSubtype<Sealed6>(x)
}


// TESTCASE NUMBER: 2
enum class Enum7 { A, B }

fun case_2(x: Enum7) {
    checkSubtype<Enum7>(x)
}


// TESTCASE NUMBER: 3
object Object8

fun case_3() {
    checkSubtype<Object8>(Object8)
}


// TESTCASE NUMBER: 4
open class Base9A
interface Base9B

open class Middle9 : Base9A(), Base9B

class Derived9 : Middle9()

fun case_4(x: Derived9) {
    checkSubtype<Base9A>(x)
    checkSubtype<Base9B>(x)
    checkSubtype<Middle9>(x)
}


// TESTCASE NUMBER: 5
class Case5

fun case_5(x: Case5) {
    checkSubtype<Any>(x)
    checkSubtype<Case5>(x)
}
