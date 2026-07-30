// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 266 -> sentence 266
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 266 -> sentence 266
 *                declarations, property-declaration -> paragraph 266 -> sentence 266
 * NUMBER: 1
 * DESCRIPTION: precise types when a class reads its own private members through public methods
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vault(private val secret: Int) {
    fun get(): Int = secret
}

fun case1() {
    val v = Vault(1)
    v checkType { check<Vault>() }
    v.get() checkType { check<Int>() }
    checkSubtype<Int>(v.get())
}

// TESTCASE NUMBER: 2
class Lock {
    private fun code(): Int = 2
    fun open(): Int = code()
}

fun case2() {
    val lock = Lock()
    lock checkType { check<Lock>() }
    lock.open() checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
class Capsule {
    private val hidden: String = "x"
    fun reveal(): String = hidden
    fun length(): Int = hidden.length
}

fun case3() {
    val c = Capsule()
    c.reveal() checkType { check<String>() }
    c.length() checkType { check<Int>() }
}
