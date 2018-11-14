this.EXEP_INVALID_TYPE = 'Invalid type! Should be \'number\' or \'string\'';

this.ranks = ['Muted', 'Member', 'Mod', 'Admin'];
this.perms = {
	BAN: 'ban',
	MUTE: 'mute',
	RANK: 'rank',
	TALK: 'talk',
	EXIST: 'exist'
}
this.allowed = {
	BAN: 2,
	MUTE: 2,
	RANK: 3,
	TALK: 1,
	EXIST: 0
}

this.rank = function (rankDescriminator){
	var type = typeof(rankDescriminator);

	if(type === 'number'){
		return this.ranks[rankDescriminator];
	}
	else if(type === 'string'){
		return this.ranks.indexOf(rankDescriminator);
	}
	
	throw EXEP_INVALID_TYPE;
}

this.canDo = function (rank, perm){
	var type = typeof(rank);

	if(!allowed[perm]){
		throw 'Permission does not exist!';
	}

	if(type === 'string'){
		return allowed[perm] <= this.rank(rank);
	}
	else if(type === 'number'){
		return allowed[perm] <= rank;
	}

	throw EXEP_INVALID_TYPE;
}

if(this.module){
	module.exports = this;
}