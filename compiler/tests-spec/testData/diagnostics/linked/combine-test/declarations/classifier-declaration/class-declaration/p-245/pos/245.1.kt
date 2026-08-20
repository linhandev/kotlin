// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 245 -> sentence 245
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 245 -> sentence 245
 *                inheritance, inheriting -> paragraph 245 -> sentence 245
 * NUMBER: 1
 * DESCRIPTION: precise types when a class implements two independent generic interfaces with different type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface A<T> {
    fun ta(): T
}

interface B<U> {
    fun tb(): U
}

class IntStringBoth : A<Int>, B<String> {
    override fun ta(): Int = 1
    override fun tb(): String = "s"
}

fun case1() {
    val c = IntStringBoth()
    c checkType { check<IntStringBoth>() }
    checkSubtype<A<Int>>(c)
    checkSubtype<B<String>>(c)
    c.ta() checkType { check<Int>() }
    c.tb() checkType { check<String>() }
}

// TESTCASE NUMBER: 2
interface Left<T> {
    val left: T
}

interface Right<U> {
    val right: U
}

class BoolLongBoth : Left<Boolean>, Right<Long> {
    override val left: Boolean = true
    override val right: Long = 9L
}

fun case2() {
    val c = BoolLongBoth()
    checkSubtype<Left<Boolean>>(c)
    checkSubtype<Right<Long>>(c)
    c.left checkType { check<Boolean>() }
    c.right checkType { check<Long>() }
}

// TESTCASE NUMBER: 3
interface Producer<T> {
    fun produce(): T
}

interface Consumer<U> {
    fun accept(x: U): U
}

class MixBoth : Producer<String>, Consumer<Int> {
    override fun produce(): String = "p"
    override fun accept(x: Int): Int = x + 1
}

fun case3() {
    val c = MixBoth()
    checkSubtype<Producer<String>>(c)
    checkSubtype<Consumer<Int>>(c)
    c.produce() checkType { check<String>() }
    c.accept(2) checkType { check<Int>() }
}
