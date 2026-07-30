// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 255 -> sentence 255
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 255 -> sentence 255
 *                type-system, introduction-1 -> paragraph 255 -> sentence 255
 *                inheritance, inheriting -> paragraph 255 -> sentence 255
 * NUMBER: 1
 * DESCRIPTION: precise nullable types when a class implements a generic interface with a nullable type argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Holder<T> {
    val v: T
}

class NullStringHolder : Holder<String?> {
    override val v: String? = null
}

fun case1() {
    val h = NullStringHolder()
    h checkType { check<NullStringHolder>() }
    checkSubtype<Holder<String?>>(h)
    h.v checkType { check<String?>() }
    checkSubtype<String?>(h.v)
}

// TESTCASE NUMBER: 2
interface Box<T> {
    fun get(): T
}

class PresentIntBox : Box<Int?> {
    override fun get(): Int? = 7
}

fun case2() {
    val b = PresentIntBox()
    checkSubtype<Box<Int?>>(b)
    b.get() checkType { check<Int?>() }
}

// TESTCASE NUMBER: 3
interface Factory<T> {
    fun create(): T
}

class NullBoolFactory : Factory<Boolean?> {
    override fun create(): Boolean? = null
}

fun case3() {
    val f = NullBoolFactory()
    checkSubtype<Factory<Boolean?>>(f)
    f.create() checkType { check<Boolean?>() }
}
