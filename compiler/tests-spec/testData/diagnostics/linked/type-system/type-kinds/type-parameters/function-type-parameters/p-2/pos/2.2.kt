// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, function-type-parameters -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Function type parameters with class and interface bounds are well-formed
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> case_1(x: T) {
    checkSubtype<T>(x)
}


// TESTCASE NUMBER: 2
fun <T : CharSequence> case_2(x: T): Int = x.length


// TESTCASE NUMBER: 3
fun case_3() {
    fun <T> local(value: T): T = value
    checkSubtype<String>(local("ok"))
}


// TESTCASE NUMBER: 4
interface Case4<T> {
    fun get(): T
}

fun <T> case_4(factory: () -> T): Case4<T> = object : Case4<T> {
    override fun get(): T = factory()
}


// TESTCASE NUMBER: 5
fun <T> case_5(x: T?) {
    val y: T? = x
    checkSubtype<T?>(y)
}
