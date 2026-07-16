// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: extension on superclass, open class, and interface is applicable to concrete subtype receivers
 */

// TESTCASE NUMBER: 1
open class Animal

class Dog : Animal()

fun Animal.label(): String = "animal"

fun dispatchOnSubtype(receiver: Dog): String = receiver.label()

// TESTCASE NUMBER: 2
open class Base

class Derived : Base()

fun Base.kind(): String = "base"

fun resolveOnDerived(receiver: Derived): String = receiver.kind()

// TESTCASE NUMBER: 3
interface Named {
    val name: String
}

class Person(override val name: String) : Named

fun Named.greeting(): String = "hello, $name"

fun dispatchOnInterface(receiver: Person): String = receiver.greeting()
