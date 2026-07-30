// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 198 -> sentence 198
 * PRIMARY LINKS: inheritance, overriding -> paragraph 198 -> sentence 198
 *                declarations, declarations-with-type-parameters -> paragraph 198 -> sentence 198
 *                inheritance, inheriting -> paragraph 198 -> sentence 198
 * NUMBER: 1
 * DESCRIPTION: type inference when overriding a member of a generic class with a consistent substituted signature in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Box<T> {
    open fun get(): T? = null
}

class IntBox : Box<Int>() {
    override fun get(): Int? = 1
}

fun case1() {
    val box = IntBox()
    box checkType { check<IntBox>() }
    checkSubtype<Box<Int>>(box)
    box.get() checkType { check<Int?>() }

    val asBox: Box<Int> = box
    asBox.get() checkType { check<Int?>() }
}

// TESTCASE NUMBER: 2
open class Holder<T>(open val item: T)

class StringHolder(item: String) : Holder<String>(item) {
    override val item: String get() = super.item + "!"
}

fun case2() {
    val h = StringHolder("hi")
    h checkType { check<StringHolder>() }
    checkSubtype<Holder<String>>(h)
    h.item checkType { check<String>() }
}

// TESTCASE NUMBER: 3
open class PairBox<A, B> {
    open fun left(): A? = null
    open fun right(): B? = null
}

class StringIntBox : PairBox<String, Int>() {
    override fun left(): String? = "ok"
    override fun right(): Int? = 7
}

fun case3() {
    val p = StringIntBox()
    p checkType { check<StringIntBox>() }
    checkSubtype<PairBox<String, Int>>(p)
    p.left() checkType { check<String?>() }
    p.right() checkType { check<Int?>() }
}
