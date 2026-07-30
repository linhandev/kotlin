// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 234 -> sentence 234
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 234 -> sentence 234
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 234 -> sentence 234
 * NUMBER: 1
 * DESCRIPTION: invariant generic interface type arguments are not subtypes (Box<Int> cannot be assigned to Box<Number>); contrasts with p-8/p-134 class Box invariance and with next-point covariant out T producer assignment
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun get(): T
}

class IntBox : Box<Int> {
    override fun get(): Int = 1
}

fun case1() {
    val x: Box<Number> = <!INITIALIZER_TYPE_MISMATCH!>IntBox()<!>
}

// TESTCASE NUMBER: 2
interface Holder<T> {
    fun value(): T
}

class StringHolder : Holder<String> {
    override fun value(): String = "x"
}

fun case2() {
    val y: Holder<Any> = <!INITIALIZER_TYPE_MISMATCH!>StringHolder()<!>
}

// TESTCASE NUMBER: 3
interface Bag<T> {
    fun item(): T
}

class StringBag : Bag<String> {
    override fun item(): String = "y"
}

fun case3() {
    val z: Bag<CharSequence> = <!INITIALIZER_TYPE_MISMATCH!>StringBag()<!>
}
