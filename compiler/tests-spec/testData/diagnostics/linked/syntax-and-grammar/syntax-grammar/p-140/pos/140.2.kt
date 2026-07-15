// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 140 -> sentence 140
 * NUMBER: 2
 * DESCRIPTION: equalityOperator not equals and identity operators
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p140.pos2

fun case1() {
    val original = "Kotlin"
    val sameReference = original
    val sameRefTrue = original === sameReference
    val sameRefFalse = original !== sameReference
    
    val content1 = String("Kotlin".toCharArray())
    val content2 = String("Kotlin".toCharArray())
    val diffRefFalse = content1 === content2
    val diffRefTrue = content1 !== content2
    
    val structuralEqual = content1 == content2
    val referentialEqual = content1 === content2
    
    val nullable: String? = "test"
    val nullValue: String? = null
    val nullCompareTrue = nullValue === null
    val nullCompareFalse = nullable === nullValue
    val nullCompareNe = nullable !== nullValue
    
    class User(val name: String)
    val user1 = User("Alice")
    val user2 = User("Alice")
    val user3 = user1
    
    val userRefTrue = user1 === user3
    val userRefFalse = user1 === user2
    val userStructFalse = user1 == user2
}