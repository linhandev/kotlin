// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 247 -> sentence 247
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 247 -> sentence 247
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 247 -> sentence 247
 *                inheritance, inheriting -> paragraph 247 -> sentence 247
 * NUMBER: 1
 * DESCRIPTION: star-projected interface references remain readable as Any? while preserving runtime producer values; contrasts with p-231 fixed producers and next-point star-projected writes
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun get(): T
}

class IntBox(private val v: Int) : Box<Int> {
    override fun get(): Int = v
}

fun readBox(b: Box<*>): Any? = b.get()

// TESTCASE NUMBER: 2
interface Holder<T> {
    val current: T
}

class StringHolder(override val current: String) : Holder<String>

fun readHolder(h: Holder<*>): Any? = h.current

// TESTCASE NUMBER: 3
interface Factory<T> {
    fun create(): T
}

fun readFactory(f: Factory<*>): Any? = f.create()

fun box(): String {
    if (readBox(IntBox(7)) != 7) return "NOK: int-box"
    if (readBox(object : Box<String> { override fun get(): String = "s" }) != "s") return "NOK: string-box"
    val asStar: Box<*> = IntBox(7)
    if (asStar.get() != 7) return "NOK: via-star-box"

    if (readHolder(StringHolder("v")) != "v") return "NOK: string-holder"
    val holderStar: Holder<*> = StringHolder("v")
    if (holderStar.current != "v") return "NOK: via-star-holder"

    if (readFactory(object : Factory<Boolean> { override fun create(): Boolean = true }) != true) return "NOK: bool-factory"
    return "OK"
}
