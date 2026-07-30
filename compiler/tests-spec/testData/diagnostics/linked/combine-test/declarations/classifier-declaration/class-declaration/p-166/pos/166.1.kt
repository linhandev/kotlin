// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 166 -> sentence 166
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 166 -> sentence 166
 *                inheritance, inheriting -> paragraph 166 -> sentence 166
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 166 -> sentence 166
 * NUMBER: 1
 * DESCRIPTION: type inference when subclasses fix or forward generic superclass type arguments together with constructor values in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Box<T>(val value: T)

class IntBox(value: Int, val scale: Int) : Box<Int>(value * scale)

fun case1() {
    val box = IntBox(3, 4)
    box checkType { check<IntBox>() }
    checkSubtype<Box<Int>>(box)
    box.value checkType { check<Int>() }
    box.scale checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class PairBox<A, B>(val first: A, val second: B)

class StringIntBox(text: String, number: Int, val separator: String) :
    PairBox<String, Int>(text + separator, number + 1)

fun case2() {
    val pair = StringIntBox("a", 2, ":")
    pair checkType { check<StringIntBox>() }
    checkSubtype<PairBox<String, Int>>(pair)
    pair.first checkType { check<String>() }
    pair.second checkType { check<Int>() }
    pair.separator checkType { check<String>() }
}

// TESTCASE NUMBER: 3
open class GenericHolder<T>(val item: T)

class ForwardingHolder<T>(item: T, val copies: Int) : GenericHolder<T>(item) {
    fun repeated(): List<T> = List(copies) { this.item }
}

fun case3() {
    val holder = ForwardingHolder("k", 3)
    holder checkType { check<ForwardingHolder<String>>() }
    checkSubtype<GenericHolder<String>>(holder)
    holder.item checkType { check<String>() }
    holder.copies checkType { check<Int>() }
    holder.repeated() checkType { check<List<String>>() }
}
