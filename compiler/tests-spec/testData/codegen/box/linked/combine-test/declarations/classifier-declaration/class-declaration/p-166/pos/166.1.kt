// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 166 -> sentence 166
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 166 -> sentence 166
 *                inheritance, inheriting -> paragraph 166 -> sentence 166
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 166 -> sentence 166
 * NUMBER: 1
 * DESCRIPTION: generic superclass type arguments are fixed or forwarded by subclasses while constructor values are delegated consistently in class declaration
 */

// TESTCASE NUMBER: 1
open class Box<T>(val value: T)

class IntBox(value: Int, val scale: Int) : Box<Int>(value * scale)

// TESTCASE NUMBER: 2
open class PairBox<A, B>(val first: A, val second: B)

class StringIntBox(text: String, number: Int, val separator: String) :
    PairBox<String, Int>(text + separator, number + 1)

// TESTCASE NUMBER: 3
open class GenericHolder<T>(val item: T)

class ForwardingHolder<T>(item: T, val copies: Int) : GenericHolder<T>(item) {
    fun repeated(): List<T> = List(copies) { this.item }
}

fun box(): String {
    val intBox = IntBox(3, 4)
    if (intBox.value != 12 || intBox.scale != 4) return "NOK: int-box"
    if ((IntBox(5, 2) as Box<Int>).value != 10) return "NOK: int-box-base"

    val pair = StringIntBox("a", 2, ":")
    if (pair.first != "a:" || pair.second != 3 || pair.separator != ":") return "NOK: pair"
    if ((StringIntBox("x", 0, "-") as PairBox<String, Int>).first != "x-") return "NOK: pair-base"

    val strings = ForwardingHolder("k", 3)
    if (strings.repeated() != listOf("k", "k", "k")) return "NOK: strings"
    val numbers = ForwardingHolder(7, 2)
    if (numbers.item != 7 || numbers.repeated() != listOf(7, 7)) return "NOK: numbers"
    return "OK"
}
