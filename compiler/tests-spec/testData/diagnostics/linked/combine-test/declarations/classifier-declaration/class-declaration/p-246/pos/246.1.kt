// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 246 -> sentence 246
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 246 -> sentence 246
 *                declarations, function-declaration -> paragraph 246 -> sentence 246
 *                inheritance, inheriting -> paragraph 246 -> sentence 246
 * NUMBER: 1
 * DESCRIPTION: precise types when a class uses generic interface default members that reference the type parameter through abstract members
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun get(): T
    fun isNull(): Boolean = get() == null
}

class StringBox : Box<String> {
    override fun get(): String = "a"
}

fun case1() {
    val b = StringBox()
    b checkType { check<StringBox>() }
    checkSubtype<Box<String>>(b)
    b.get() checkType { check<String>() }
    b.isNull() checkType { check<Boolean>() }
}

// TESTCASE NUMBER: 2
interface Holder<T> {
    fun value(): T
    fun label(): String = "v=" + value().toString()
}

class IntHolder : Holder<Int> {
    override fun value(): Int = 7
}

fun case2() {
    val h = IntHolder()
    checkSubtype<Holder<Int>>(h)
    h.value() checkType { check<Int>() }
    h.label() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
interface PairBox<A, B> {
    fun left(): A
    fun right(): B
    fun joined(): String = left().toString() + ":" + right().toString()
}

class MixPair : PairBox<String, Int> {
    override fun left(): String = "x"
    override fun right(): Int = 2
}

fun case3() {
    val p = MixPair()
    checkSubtype<PairBox<String, Int>>(p)
    p.left() checkType { check<String>() }
    p.right() checkType { check<Int>() }
    p.joined() checkType { check<String>() }
}
