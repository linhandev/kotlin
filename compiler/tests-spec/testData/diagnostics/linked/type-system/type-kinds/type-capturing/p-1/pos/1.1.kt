// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -REDUNDANT_PROJECTION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-capturing -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, type-containment -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Type capturing creates captured types when instantiating type constructors with variance
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Root1<T>

interface A1

interface B1 : A1

interface C1 : B1

interface Bounded1<T : A1> : Root1<T>

fun case_1(bounded: Bounded1<in B1>) {
    val test: Root1<in C1> = bounded
    checkSubtype<Root1<in C1>>(bounded)
}


// TESTCASE NUMBER: 2
interface Root2<T>

interface Out2<out T>

interface B2

interface Foo2<T> : Root2<Out2<T>>

fun case_2(foo: Foo2<out B2>) {
    val test: Root2<out Out2<B2>> = foo
    checkSubtype<Root2<out Out2<B2>>>(foo)
}


// TESTCASE NUMBER: 3
interface OutBox3<out T>

fun case_3(x: OutBox3<Int>) {
    checkSubtype<OutBox3<Number>>(x)
    val y: OutBox3<Number> = x
}


// TESTCASE NUMBER: 4
interface InBox4<in T>

fun case_4(x: InBox4<Number>) {
    checkSubtype<InBox4<Int>>(x)
    val y: InBox4<Int> = x
}


// TESTCASE NUMBER: 5
interface Inv5<T>

fun case_5(x: Inv5<Int>) {
    checkSubtype<Inv5<*>>(x)
    val y: Inv5<*> = x
}
