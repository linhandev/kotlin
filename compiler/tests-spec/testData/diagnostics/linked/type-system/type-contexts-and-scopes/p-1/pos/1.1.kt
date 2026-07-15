// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-contexts-and-scopes -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Types interoperate with scopes like values: qualified names, aliases, and declaring scope
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer1 {
    class Nested
}

fun case_1(): Outer1.Nested {
    val nested = Outer1.Nested()
    checkSubtype<Outer1.Nested>(nested)
    return nested
}


// TESTCASE NUMBER: 2
typealias Name2 = String

fun case_2(x: Name2): Name2 {
    checkSubtype<String>(x)
    return x
}


// TESTCASE NUMBER: 3
class Box3<T>(val v: T) {
    fun get(): T = v
}

fun case_3(): Int {
    val box = Box3(1)
    checkSubtype<Int>(box.get())
    return box.get()
}


// TESTCASE NUMBER: 4
class Parent4 {
    class Child
}

fun case_4(): Parent4.Child {
    val child = Parent4.Child()
    checkSubtype<Parent4.Child>(child)
    return child
}


// TESTCASE NUMBER: 5
class A5 {
    class B {
        class C
    }
}

fun case_5(): A5.B.C {
    val c = A5.B.C()
    checkSubtype<A5.B.C>(c)
    return c
}
