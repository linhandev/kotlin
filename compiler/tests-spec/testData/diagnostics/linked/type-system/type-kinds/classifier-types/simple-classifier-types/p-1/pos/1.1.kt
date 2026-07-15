// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, classifier-types, simple-classifier-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, subtyping, subtyping-rules -> paragraph 2 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Well-formed simple classifier types with type name T and optional supertypes
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Base1


// TESTCASE NUMBER: 2
interface Base2

interface Derived2 : Base2

fun case_2(x: Derived2) {
    checkSubtype<Base2>(x)
    checkSubtype<Derived2>(x)
}


// TESTCASE NUMBER: 3
open class Base3

class Derived3 : Base3()

fun case_3(x: Derived3) {
    checkSubtype<Base3>(x)
    checkSubtype<Derived3>(x)
}


// TESTCASE NUMBER: 4
interface Interface4A
interface Interface4B

class Case4 : Interface4A, Interface4B

fun case_4(x: Case4) {
    checkSubtype<Interface4A>(x)
    checkSubtype<Interface4B>(x)
}


// TESTCASE NUMBER: 5
abstract class Abstract5

class Concrete5 : Abstract5()

fun case_5(x: Concrete5) {
    checkSubtype<Abstract5>(x)
}
