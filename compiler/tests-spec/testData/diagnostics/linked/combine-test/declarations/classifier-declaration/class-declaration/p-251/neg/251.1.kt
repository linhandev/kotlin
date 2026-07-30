// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 251 -> sentence 251
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 251 -> sentence 251
 *                inheritance, overriding -> paragraph 251 -> sentence 251
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 251 -> sentence 251
 * NUMBER: 1
 * DESCRIPTION: overriding a generic interface producer with an incompatible return type fails (RETURN_TYPE_MISMATCH_ON_OVERRIDE / PROPERTY_TYPE_MISMATCH_ON_OVERRIDE); contrasts with p-250 covariant narrowing success and with p-75/p-201 class-base return mismatches
 */

// TESTCASE NUMBER: 1
interface Factory<T> {
    fun create(): T
}

class BadFactory : Factory<Int> {
    override fun create(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>String<!> = "x"
}

// TESTCASE NUMBER: 2
interface Source<T> {
    fun text(): T
}

class BadSource : Source<String> {
    override fun text(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Int<!> = 1
}

// TESTCASE NUMBER: 3
interface Holder<T> {
    val current: T
}

class BadHolder : Holder<Boolean> {
    override val current: <!PROPERTY_TYPE_MISMATCH_ON_OVERRIDE!>String<!> = "x"
}
